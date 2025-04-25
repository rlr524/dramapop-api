package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Drama {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private Integer year;
    private Integer episodes;
    private Double averageRating;
    private String iconURL;
    @CreatedDate
    private LocalDateTime dateAdded;
    @LastModifiedDate
    private LocalDateTime dateModified;

    public Drama(String title, String description, Integer year, Integer episodes, String iconURL) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.episodes = episodes;
        this.iconURL = iconURL;
    }
}
