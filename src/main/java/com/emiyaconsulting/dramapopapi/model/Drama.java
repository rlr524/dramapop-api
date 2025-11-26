package com.emiyaconsulting.dramapopapi.model;

import java.util.Set;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    
    @NonNull
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
    
    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country origin;
    
    @OneToMany(mappedBy = "drama", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DramaPersonRole> castCrew = new HashSet<>();
    
    @Column(name = "date_added") @CreationTimestamp
    private Instant dateAdded;
    
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    public Drama(@NonNull String title, String description, Integer year, Integer episodes, String iconUrl) {
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
        if (!(o instanceof Drama drama)) return false;

        return Objects.equals(id, drama.id) && title.equals(drama.title) 
                && Objects.equals(description, drama.description) && Objects.equals(year, drama.year) 
                && Objects.equals(episodes, drama.episodes) && Objects.equals(averageRating, drama.averageRating) 
                && Objects.equals(iconUrl, drama.iconUrl) && Objects.equals(origin, drama.origin) 
                && Objects.equals(castCrew, drama.castCrew) && Objects.equals(dateAdded, drama.dateAdded) 
                && Objects.equals(dateModified, drama.dateModified);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + title.hashCode();
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(year);
        result = 31 * result + Objects.hashCode(episodes);
        result = 31 * result + Objects.hashCode(averageRating);
        result = 31 * result + Objects.hashCode(iconUrl);
        result = 31 * result + Objects.hashCode(origin);
        result = 31 * result + Objects.hashCode(castCrew);
        result = 31 * result + Objects.hashCode(dateAdded);
        result = 31 * result + Objects.hashCode(dateModified);
        return result;
    }
}
