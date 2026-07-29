package com.emiyaconsulting.dramapopapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "genre")
@ToString
@Getter @Setter @NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    @NotNull
    private String name;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "date_deleted")
    private Instant dateDeleted;
    @Column(name = "date_added", nullable = false, updatable = false) @CreationTimestamp
    private Instant dateAdded;
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;

        return id != null && Objects.equals(id, genre.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
