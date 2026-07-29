package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
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
    @NotNull
    @ToString.Exclude
    @Column(name = "email", nullable = false)
    private String email;
    @NotNull
    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "pfp_url")
    private String pfpUrl;
    @Column(name = "active")
    private boolean active;
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
        if (!(o instanceof User user)) return false;

        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
