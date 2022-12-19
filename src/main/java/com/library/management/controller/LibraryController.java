package com.library.management.controller;

import com.library.management.payloads.ApiResponse;
import com.library.management.payloads.BookDto;
import com.library.management.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@Slf4j
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    // To add a new Book into the Library
    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBooks(@RequestBody BookDto bookDto) {
        log.info("Add books in library");
        BookDto addBook = this.libraryService.addBook(bookDto);
        return new ResponseEntity<>(addBook, HttpStatus.CREATED);
    }

    // To fetch a Book by its ID
    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("bookId") Integer bookId) {
        log.info("Getting a book.");
        BookDto bookDto = this.libraryService.getBookById(bookId);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    // To fetch a Book by Publisher's name
    @GetMapping("/getBookByPublisher/{publisherName}")
    public ResponseEntity<BookDto> getBookByPublisherName(@PathVariable("publisherName") String publisherName) {
        log.info("Getting a book by publisher name.");
        BookDto bookDto = this.libraryService.getBookByPublisherName(publisherName);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    // To fetch a Book by its name
    @GetMapping("/getBookByName/{bookName}")
    public ResponseEntity<BookDto> getBookByAuthorName(@PathVariable("bookName") String bookName) {
        log.info("Getting a book by name.");
        BookDto bookDto = this.libraryService.getBookByAuthorName(bookName);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    // To fetch all the Books present in Library
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = "bookName", required = false) String sortBooks
    ) {
        log.info("Getting all books from the library.");
        List<BookDto> books = this.libraryService.getAllBooks(pageNumber, pageSize, sortBooks);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // To delete a Book from the Library
    @DeleteMapping("/deleteById/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("bookId") Integer bookId) {
        log.info("Deleting book.");
        String message = this.libraryService.deleteBook(bookId);
        return ResponseEntity.ok(new ApiResponse(message, true));
    }
}
