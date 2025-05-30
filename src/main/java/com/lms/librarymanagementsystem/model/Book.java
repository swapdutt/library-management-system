package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    Author author;
    String isbn;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    Category category;
    String description;
    LocalDate publicationDate;
    String genre;
    Integer numberOfCopies;
    Boolean available;

    public void updateAvailability() {
        this.available = this.numberOfCopies > 0;
    }

    public void returnBooks() {
        numberOfCopies++;
        updateAvailability();
    }

    public void borrow() {
        if (numberOfCopies <= 0) {
            throw new IllegalStateException("No copies available");
        }
        numberOfCopies--;
        updateAvailability();
    }

}
