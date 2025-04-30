package com.emiyaconsulting.dramapopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user")
@ToString
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @NonNull
    private String email;
    private String pfpUrl;
    @CreatedDate
    private LocalDateTime dateAdded;
    @LastModifiedDate
    private LocalDateTime dateModified;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id.equals(user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && email.equals(user.email) && Objects.equals(pfpUrl, user.pfpUrl) && dateAdded.equals(user.dateAdded) && dateModified.equals(user.dateModified);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        result = 31 * result + email.hashCode();
        result = 31 * result + Objects.hashCode(pfpUrl);
        result = 31 * result + dateAdded.hashCode();
        result = 31 * result + dateModified.hashCode();
        return result;
    }
}
