package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "drama")
@ToString
@Getter @Setter @NoArgsConstructor
public class Drama {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    @Column(name = "date_added") @CreationTimestamp
    private Instant dateAdded;
    @Column(name = "date_modified") @UpdateTimestamp
    private Instant dateModified;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Drama drama = (Drama) o;
        return id.equals(drama.id) && title.equals(drama.title) && Objects.equals(description, drama.description) && Objects.equals(year, drama.year) && Objects.equals(episodes, drama.episodes) && Objects.equals(averageRating, drama.averageRating) && Objects.equals(iconUrl, drama.iconUrl) && dateAdded.equals(drama.dateAdded) && dateModified.equals(drama.dateModified);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(year);
        result = 31 * result + Objects.hashCode(episodes);
        result = 31 * result + Objects.hashCode(averageRating);
        result = 31 * result + Objects.hashCode(iconUrl);
        result = 31 * result + dateAdded.hashCode();
        result = 31 * result + dateModified.hashCode();
        return result;
    }
}
