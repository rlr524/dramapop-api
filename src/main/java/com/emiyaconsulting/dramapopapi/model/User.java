package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user")
@ToString
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ToString.Exclude
    @Column(name = "first_name")
    private String firstName;
    @ToString.Exclude
    @Column(name = "last_name")
    private String lastName;
    @NonNull
    @ToString.Exclude
    @Column(name = "email", nullable = false)
    private String email;
    @NonNull
    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "pfp_url")
    private String pfpUrl;
    @Column(name = "active")
    private boolean active;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "date_deleted", updatable = false)
    private Instant dateDeleted;
    @Column(name = "date_added", nullable = false, updatable = false) @CreationTimestamp
    private Instant dateAdded;
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id.equals(user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && email.equals(user.email) && Objects.equals(pfpUrl, user.pfpUrl) && dateAdded.equals(user.dateAdded) && dateModified.equals(user.dateModified);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        result = 31 * result + email.hashCode();
        result = 31 * result + Objects.hashCode(pfpUrl);
        result = 31 * result + dateAdded.hashCode();
        result = 31 * result + dateModified.hashCode();
        return result;
    }
}
