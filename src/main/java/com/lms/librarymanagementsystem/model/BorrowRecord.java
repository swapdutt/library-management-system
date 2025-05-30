package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "borrow_record")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    LocalDate borrowDate;
    LocalDate dueDate;
    @OneToMany(mappedBy = "borrowRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<BorrowItem> items = new ArrayList<>();

    public void addItem(BorrowItem item) {
        item.setBorrowRecord(this);
        items.add(item);
    }

}
