package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "broadcast_service")
@ToString
@Getter @Setter @NoArgsConstructor
public class BroadcastService {
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
        if (!(o instanceof BroadcastService service)) return false;

        return Objects.equals(id, service.id) && name.equals(service.name) 
                && Objects.equals(dateAdded, service.dateAdded) && Objects.equals(dateModified, service.dateModified);
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
