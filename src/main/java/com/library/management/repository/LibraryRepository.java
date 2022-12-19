package com.library.management.repository;

import com.library.management.entity.Author;
import com.library.management.entity.Book;
import com.library.management.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Book,Integer> {

    Book findByAuthorId(Author author);

    Book findByPublisherId(Publisher publisher);
}
