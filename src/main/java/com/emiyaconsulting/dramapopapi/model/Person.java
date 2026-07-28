package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
@Getter @Setter @NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "pfp_url")
    private String pfpUrl;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DramaPersonRole> castCrew = new HashSet<>();
    
    @Column(name = "date_added") @CreationTimestamp
    private LocalDateTime dateAdded;
    
    @Column(name = "date_modified") @UpdateTimestamp
    private LocalDateTime dateModified;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;

        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) 
                && Objects.equals(lastName, person.lastName) && Objects.equals(pfpUrl, person.pfpUrl) 
                && Objects.equals(castCrew, person.castCrew) && Objects.equals(dateAdded, person.dateAdded) 
                && Objects.equals(dateModified, person.dateModified);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        result = 31 * result + Objects.hashCode(pfpUrl);
        result = 31 * result + Objects.hashCode(castCrew);
        result = 31 * result + Objects.hashCode(dateAdded);
        result = 31 * result + Objects.hashCode(dateModified);
        return result;
    }
}

