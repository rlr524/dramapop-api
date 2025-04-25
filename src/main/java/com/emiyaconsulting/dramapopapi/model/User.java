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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String pfpURL;
    @CreatedDate
    private LocalDateTime dateAdded;
    @LastModifiedDate
    private LocalDateTime dateModified;

    public User(String firstName, String lastName, String email, String pfpURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pfpURL = pfpURL;
    }
}
