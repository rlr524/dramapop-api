package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
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
    private String title;
    private String description;
    private Integer year;
    private Integer episodes;
    private Double averageRating;
    private String iconUrl;
    @CreatedDate
    private LocalDateTime dateAdded;
    @LastModifiedDate
    private LocalDateTime dateModified;

    public Drama(@NonNull String title, String description, Integer year, Integer episodes, String iconURL) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.episodes = episodes;
        this.iconUrl = iconURL;
    }

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
