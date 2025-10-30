package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "role")
@ToString
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date_added") @CreationTimestamp
    private LocalDateTime dateAdded;
    @Column(name = "date_modified") @UpdateTimestamp
    private LocalDateTime dateModified;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Role role)) return false;

        return id.equals(role.id) && name.equals(role.name) && dateAdded.equals(role.dateAdded) && dateModified.equals(role.dateModified);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + dateAdded.hashCode();
        result = 31 * result + dateModified.hashCode();
        return result;
    }
}
