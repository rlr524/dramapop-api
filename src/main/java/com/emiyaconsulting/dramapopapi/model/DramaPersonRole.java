package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "drama_person_role")
@ToString
@Getter @Setter @NoArgsConstructor
public class DramaPersonRole {
    @EmbeddedId
    private DramaPersonRoleId id; // Composite primary key

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dramaId") // Maps the dramaId from the embedded ID
    @JoinColumn(name = "drama_id")
    private Drama drama;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId") // Maps the personId from the embedded ID
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId") // Maps the roleId from the embedded ID
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DramaPersonRole that)) return false;

        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
