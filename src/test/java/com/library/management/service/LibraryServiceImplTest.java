package com.library.management.service;

import com.library.management.entity.Author;
import com.library.management.entity.Book;
import com.library.management.entity.Publisher;
import com.library.management.payloads.BookDto;
import com.library.management.repository.AuthorRepo;
import com.library.management.repository.LibraryRepository;
import com.library.management.repository.PublisherRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class LibraryServiceImplTest {

    @Mock
    LibraryRepository libraryRepository;

    @Mock
    AuthorRepo authorRepo;

    @Mock
    PublisherRepo publisherRepo;

    @InjectMocks
    LibraryServiceImpl libraryServiceImpl;

    BookDto bookDto = null;
    Author author = null;
    Publisher publisher = null;
    Book book = null;

    @BeforeEach
    public void setUp() {
        bookDto = new BookDto("ABC", "Test", "Test", "Test");
        author = new Author();
        author.setAuthorName("ABC");
        author.setAuthorId(1);
        publisher = new Publisher();
        publisher.setPublisherName("XYZ");
        publisher.setPublisherId(1);
        book = new Book();
        book.setBookName(bookDto.getBookName());
        book.setPublishDate(bookDto.getPublishDate());
        book.setPublisherId(publisher);
        book.setAuthorId(author);
    }

    @Test
    void addBook() {
        when(authorRepo.findByAuthorName(bookDto.getAuthorName())).thenReturn(author);

        when(publisherRepo.findByPublisherName(bookDto.getPublisherName())).thenReturn(publisher);

        when(libraryRepository.save(book)).thenReturn(book);
        assertEquals(bookDto, libraryServiceImpl.addBook(bookDto));
    }

    @Test
    void getBookById() {
        Integer bookId = 1;
        when(libraryRepository.findById(bookId)).thenReturn(Optional.of(book));
        assertEquals(bookDto.getBookName(), libraryServiceImpl.getBookById(bookId).getBookName());
    }

    @Test
    void deleteBook() {
        Integer bookId = 1;
        libraryServiceImpl.deleteBook(bookId);
        verify(libraryRepository, times(1)).deleteById(bookId);
    }

    @Test
    void getBookByAuthorName() {
        String authorName = "XYZ";
        when(authorRepo.findByAuthorName(authorName)).thenReturn(author);
        when(libraryRepository.findByAuthorId(author)).thenReturn(book);
        assertEquals(bookDto.getBookName(), libraryServiceImpl.getBookByAuthorName(authorName).getBookName());
    }

    @Test
    void getBookByPublisherName() {
        String publisherName = "ABC";
        when(publisherRepo.findByPublisherName(publisherName)).thenReturn(publisher);
        when(libraryRepository.findByPublisherId(publisher)).thenReturn(book);
        assertEquals(bookDto.getBookName(), libraryServiceImpl.getBookByPublisherName(publisherName).getBookName());

    }
}