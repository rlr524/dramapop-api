package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@ToString
@Getter
@Setter
@NoArgsConstructor
public class DramaPersonRoleId implements Serializable {
    private Long dramaId;
    private Long personId;
    private Long roleId;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof DramaPersonRoleId that)) return false;

        return Objects.equals(dramaId, that.dramaId) && Objects.equals(personId, that.personId) 
                && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(dramaId);
        result = 31 * result + Objects.hashCode(personId);
        result = 31 * result + Objects.hashCode(roleId);
        return result;
    }
}
