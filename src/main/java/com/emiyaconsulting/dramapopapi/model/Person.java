package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
@ToString
@Getter @Setter @NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "pfp_url")
    private String pfpUrl;

    @ToString.Exclude
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DramaPersonRole> castCrew = new HashSet<>();
    
    @Column(name = "date_added") @CreationTimestamp
    private Instant dateAdded;

    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}

