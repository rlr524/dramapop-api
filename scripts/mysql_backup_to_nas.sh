#!/bin/bash
set -euo pipefail

BACKUP_DIR="/mnt/nas-mysql-backups"
CRED_FILE="/etc/mysql/backup-credentials/.my.cnf"
JIRA_ENV="/etc/jira/alert-credentials.env"
TIMESTAMP=$(date +%F_%H-%M-%S)
FILENAME="mysql-backup-${TIMESTAMP}.sql.gz"
FILEPATH="${BACKUP_DIR}/${FILENAME}"
LOGFILE="/var/log/mysql/backup.log"

# shellcheck source=/etc/jira/alert-credentials.env
source "$JIRA_ENV"

create_jira_alert() {
    local summary="$1"
    local description="$2"

    curl -s -o /dev/null -w "%{http_code}" \
        -u "${JIRA_EMAIL}:${JIRA_API_TOKEN}" \
        -X POST \
        -H "Content-Type: application/json" \
        "https://${JIRA_DOMAIN}/rest/api/3/issue" \
        --data "$(python3 -c "
import json, sys
payload = {
    'fields': {
        'project': {'key': '${JIRA_PROJECT_KEY}'},
        'summary': '''${summary}''',
        'issuetype': {'id': '${JIRA_ISSUE_TYPE_ID}'},
        'description': {
            'type': 'doc',
            'version': 1,
            'content': [{
                'type': 'paragraph',
                'content': [{'type': 'text', 'text': '''${description}'''}]
            }]
        }
    }
}
print(json.dumps(payload))
")" >> "$LOGFILE" 2>&1
}

echo "[$(date)] Starting backup: ${FILENAME}" >> "$LOGFILE"

if ! mountpoint -q "$BACKUP_DIR"; then
    echo "[$(date)] ERROR: ${BACKUP_DIR} is not mounted. Aborting." >> "$LOGFILE"
    create_jira_alert "MySQL backup FAILED - NAS not mounted" \
        "Backup on $(hostname) aborted on $(date) because ${BACKUP_DIR} was not mounted. Check the CIFS mount and NAS connectivity."
    exit 1
fi

mysqldump --defaults-extra-file="$CRED_FILE" \
    --all-databases \
    --single-transaction \
    --routines \
    --triggers \
    --events \
    | gzip > "$FILEPATH"

if [ $? -ne 0 ]; then
    echo "[$(date)] ERROR: mysqldump/gzip failed for ${FILENAME}." >> "$LOGFILE"
    create_jira_alert "MySQL backup FAILED - dump error" \
        "mysqldump or gzip failed on $(hostname) at $(date) while producing ${FILENAME}. Check /var/log/mysql/backup.log for details."
    exit 1
fi

echo "[$(date)] Dump written: ${FILENAME}" >> "$LOGFILE"

if gunzip -t "$FILEPATH" 2>>"$LOGFILE"; then
    echo "[$(date)] Integrity check PASSED: ${FILENAME}" >> "$LOGFILE"
else
    echo "[$(date)] ERROR: Integrity check FAILED for ${FILENAME} — archive is corrupt." >> "$LOGFILE"
    mv "$FILEPATH" "${FILEPATH}.CORRUPT"
    create_jira_alert "MySQL backup FAILED - corrupt archive" \
        "The gzip integrity check failed for ${FILENAME} on $(hostname) at $(date). File preserved as ${FILENAME}.CORRUPT on the NAS for inspection."
    exit 1
fi

find "$BACKUP_DIR" -name "mysql-backup-*.sql.gz" -type f -mtime +30 -delete
echo "[$(date)] Retention cleanup complete (30-day window)." >> "$LOGFILE"
echo "[$(date)] Backup completed successfully: ${FILENAME}" >> "$LOGFILE"