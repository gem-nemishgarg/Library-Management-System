package com.library.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bookId;
    String bookName;
    String publishDate;
    @ManyToOne(cascade = CascadeType.ALL) // update-all updated, delete-delete all corresponding to it.
    Author authorId; // We need to take the class to which we are mapping.
    @ManyToOne(cascade = CascadeType.ALL)
    Publisher publisherId;
}
