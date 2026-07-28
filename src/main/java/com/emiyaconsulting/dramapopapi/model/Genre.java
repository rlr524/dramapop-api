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
    @Column(name = "date_deleted", updatable = false)
    private Instant dateDeleted;
    @Column(name = "date_added", nullable = false, updatable = false) @CreationTimestamp
    private Instant dateAdded;
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Genre genre)) return false;

        return deleted == genre.deleted && id.equals(genre.id) && name.equals(genre.name) && Objects.equals(dateDeleted, genre.dateDeleted) && dateAdded.equals(genre.dateAdded) && dateModified.equals(genre.dateModified);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + Boolean.hashCode(deleted);
        result = 31 * result + Objects.hashCode(dateDeleted);
        result = 31 * result + dateAdded.hashCode();
        result = 31 * result + dateModified.hashCode();
        return result;
    }
}
