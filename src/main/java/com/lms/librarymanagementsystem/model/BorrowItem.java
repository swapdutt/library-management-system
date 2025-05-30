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
@Table(name = "borrow_item")
public class BorrowItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    Book book;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "borrow_record_id")
    BorrowRecord borrowRecord;
    LocalDate borrowDate;
    LocalDate dueDate;
    LocalDate returnDate;
    Boolean returned;

    public void markAsReturned() {
        this.returnDate = LocalDate.now();
        this.book.returnBooks();
        this.returned = true;
    }

}
