package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.payloads.ApiResponse;
import com.library.management.payloads.BookDto;
import com.library.management.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryControllerTest {

    @Mock
    LibraryService libraryService;

    @InjectMocks
    LibraryController libraryController;
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = null;
    BookDto bookDto = null;
    List<BookDto> bookDtoList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(libraryController).build();
        objectMapper = new ObjectMapper();
        bookDto = new BookDto();
        bookDto.setAuthorName("ABC");
        bookDto.setBookName("Chemistry");
        bookDto.setPublishDate("2021");
        bookDto.setPublisherName("DPS");
        bookDtoList.add(bookDto);
    }

    @Test
    void addBooks() throws Exception {
        when(libraryService.addBook(bookDto)).thenReturn(bookDto);
        mockMvc.perform(post("/api/library/addBook")
                        .content(objectMapper.writeValueAsString(bookDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void getBookById() throws Exception {
        Integer bookId = 1;
        when(libraryService.getBookById(bookId)).thenReturn(bookDto);
        mockMvc.perform(get("/api/library/getBookById/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(bookDto)));
    }

    @Test
    void getBookByAuthorName() throws Exception {
        String authorName = "AWS";
        when(libraryService.getBookByAuthorName(authorName)).thenReturn(bookDto);
        mockMvc.perform(get("/api/library/getBookByName/" + authorName))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(bookDto)));
    }


    @Test
    void getAllBooks() throws Exception {
        Integer pageNumber = 0;
        Integer pageSize = 5;
        String bookName = "SST";
        when(libraryService.getAllBooks(pageNumber, pageSize, bookName)).thenReturn(bookDtoList);
        mockMvc.perform(get("/api/library/getAllBooks")
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize))
                        .param("sortBy", bookName))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(bookDtoList)));
    }

    @Test
    void deleteBook() throws Exception {
        Integer bookId = 1;
        String message = "Successfully deleted the book";
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setSuccess(true);
        when(libraryService.deleteBook(bookId)).thenReturn(message);
        mockMvc.perform(delete("/api/library/deleteById/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(apiResponse)));
    }
}