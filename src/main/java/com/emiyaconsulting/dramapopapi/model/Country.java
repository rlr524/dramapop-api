package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "country")
@ToString
@Getter @Setter @NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date_added") @CreationTimestamp
    private Instant dateAdded;
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Country country)) return false;

        return Objects.equals(id, country.id) && name.equals(country.name)
                && Objects.equals(dateAdded, country.dateAdded) && Objects.equals(dateModified, country.dateModified);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + Objects.hashCode(dateAdded);
        result = 31 * result + Objects.hashCode(dateModified);
        return result;
    }
}
