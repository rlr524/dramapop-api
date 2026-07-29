package com.emiyaconsulting.dramapopapi.model;

import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "drama")
@ToString
@Getter @Setter @NoArgsConstructor
public class Drama {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "year")
    private Integer year;
    
    @Column(name = "episodes")
    private Integer episodes;
    
    @Column(name = "average_rating")
    private Double averageRating;
    
    @Column(name = "icon_url")
    private String iconUrl;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "country_id")
    private Country origin;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "drama", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DramaPersonRole> castCrew = new HashSet<>();
    
    @Column(name = "date_added") @CreationTimestamp
    private Instant dateAdded;
    
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    public Drama(String title, String description, Integer year, Integer episodes, String iconUrl) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.episodes = episodes;
        this.averageRating = 0.0;
        this.iconUrl = iconUrl;
        this.origin = null;
        this.castCrew = null;
        this.dateAdded = Instant.now();
        this.dateModified = Instant.now();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drama drama)) return false;

        return id != null && Objects.equals(id, drama.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
