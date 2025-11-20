package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "rating")
@ToString
@Getter @Setter @NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name="rating", nullable = false)
    private int rating;
    @NonNull
    @OneToOne
    @JoinColumn(name="drama_id")
    private Drama drama;
    @NonNull
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name = "date_added") @CreationTimestamp
    private Instant dateAdded;
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Rating rating)) return false;

        return Objects.equals(Id, rating.Id) && drama.equals(rating.drama) 
                && Objects.equals(user, rating.user) 
                && Objects.equals(dateAdded, rating.dateAdded) 
                && Objects.equals(dateModified, rating.dateModified);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(Id);
        result = 31 * result + drama.hashCode();
        result = 31 * result + Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(dateAdded);
        result = 31 * result + Objects.hashCode(dateModified);
        return result;
    }
}
