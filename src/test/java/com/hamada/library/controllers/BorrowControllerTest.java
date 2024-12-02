package com.hamada.library.controllers;


import com.hamada.library.domain.BorrowingRecordEntity;
import com.hamada.library.domain.BookEntity;
import com.hamada.library.domain.PatronEntity;
import com.hamada.library.services.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BorrowControllerTest {

    @Mock
    private BorrowService borrowService;

    @InjectMocks
    private BorrowController borrowController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowController).build();
    }

    @Test
    void testBorrowBook_Success() throws Exception {
        // Arrange
        Long bookId = 1L;
        Long patronId = 2L;

        // Mock dependent entities
        BookEntity bookEntity = new BookEntity();
        bookEntity.setID(bookId);

        PatronEntity patronEntity = new PatronEntity();
        patronEntity.setID(patronId);

        // Create BorrowingRecordEntity
        BorrowingRecordEntity borrowingRecord = new BorrowingRecordEntity();
        borrowingRecord.setBook(bookEntity);
        borrowingRecord.setPatron(patronEntity);
        borrowingRecord.setBorrowedDate(LocalDate.now());

        // Mocking the service method
        when(borrowService.borrowBook(bookId, patronId)).thenReturn(borrowingRecord);

        // Act and Assert
        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.book.id").value(bookId))
                .andExpect(jsonPath("$.patron.id").value(patronId))
                .andExpect(jsonPath("$.borrowedDate").value(borrowingRecord.getBorrowedDate().toString()));
    }

    @Test
    void testBorrowBook_Failure() throws Exception {
        // Arrange
        Long bookId = 1L;
        Long patronId = 2L;

        when(borrowService.borrowBook(bookId, patronId)).thenThrow(new RuntimeException("Book cannot be borrowed"));

        // Act and Assert
        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReturnBook_Success() throws Exception {
        // Arrange
        Long bookId = 1L;
        Long patronId = 2L;

        // Mock dependent entities
        BookEntity bookEntity = new BookEntity();
        bookEntity.setID(bookId);

        PatronEntity patronEntity = new PatronEntity();
        patronEntity.setID(patronId);

        // Create BorrowingRecordEntity for the returned book
        BorrowingRecordEntity borrowingRecord = new BorrowingRecordEntity();
        borrowingRecord.setBook(bookEntity);
        borrowingRecord.setPatron(patronEntity);
        borrowingRecord.setReturnDate(LocalDate.now());

        // Mocking the service method
        when(borrowService.returnBook(bookId, patronId)).thenReturn(borrowingRecord);

        // Act and Assert
        mockMvc.perform(put("/api/return/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.book.id").value(bookId))
                .andExpect(jsonPath("$.patron.id").value(patronId))
                .andExpect(jsonPath("$.returnDate").value(borrowingRecord.getReturnDate().toString()));
    }

    @Test
    void testReturnBook_Failure() throws Exception {
        // Arrange
        Long bookId = 1L;
        Long patronId = 2L;

        when(borrowService.returnBook(bookId, patronId)).thenThrow(new RuntimeException("Book cannot be returned"));

        // Act and Assert
        mockMvc.perform(put("/api/return/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isBadRequest());
    }
}
