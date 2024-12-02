package com.hamada.library.controllers;



import com.hamada.library.domain.BookEntity;
import com.hamada.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() {
        BookEntity book = new BookEntity();
        book.setTitle("Test Book");

        when(bookService.create(any(BookEntity.class))).thenReturn(book);

        ResponseEntity<BookEntity> response = bookController.create(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Book", response.getBody().getTitle());
        verify(bookService, times(1)).create(book);
    }

    @Test
    void testFindBookById_Found() {
        BookEntity book = new BookEntity();
        book.setTitle("Test Book");
        when(bookService.find(1L)).thenReturn(Optional.of(book));

        ResponseEntity<BookEntity> response = bookController.find(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Book", response.getBody().getTitle());
        verify(bookService, times(1)).find(1L);
    }

    @Test
    void testFindBookById_NotFound() {
        when(bookService.find(1L)).thenReturn(Optional.empty());

        ResponseEntity<BookEntity> response = bookController.find(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).find(1L);
    }

    @Test
    void testListBooks() {
        BookEntity book1 = new BookEntity();
        book1.setTitle("Book 1");
        BookEntity book2 = new BookEntity();
        book2.setTitle("Book 2");

        List<BookEntity> books = Arrays.asList(book1, book2);
        when(bookService.listBooks()).thenReturn(books);

        ResponseEntity<List<BookEntity>> response = bookController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(bookService, times(1)).listBooks();
    }

    @Test
    void testUpdateBook_Found() {
        BookEntity book = new BookEntity();
        book.setTitle("Updated Title");
        when(bookService.isBookExists(1L)).thenReturn(true);
        when(bookService.create(any(BookEntity.class))).thenReturn(book);

        ResponseEntity<BookEntity> response = bookController.update(book, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Title", response.getBody().getTitle());
        verify(bookService, times(1)).isBookExists(1L);
        verify(bookService, times(1)).create(book);
    }

    @Test
    void testUpdateBook_NotFound() {
        BookEntity book = new BookEntity();
        when(bookService.isBookExists(1L)).thenReturn(false);

        ResponseEntity<BookEntity> response = bookController.update(book, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).isBookExists(1L);
        verify(bookService, never()).create(any(BookEntity.class));
    }

    @Test
    void testDeleteBook_Found() {
        when(bookService.isBookExists(1L)).thenReturn(true);

        ResponseEntity<Boolean> response = bookController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(bookService, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBook_NotFound() {
        when(bookService.isBookExists(1L)).thenReturn(false);

        ResponseEntity<Boolean> response = bookController.delete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(false, response.getBody());
        verify(bookService, never()).deleteById(1L);
    }
}
