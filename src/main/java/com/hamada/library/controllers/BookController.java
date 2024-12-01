package com.hamada.library.controllers;


import com.hamada.library.domain.BookEntity;
import com.hamada.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService){
        this.bookService = bookService;
    }


    @PostMapping("/api/books")
    public ResponseEntity<BookEntity> create(
        @RequestBody final BookEntity bookEntity){

        final BookEntity savedBookEntity = bookService.create(bookEntity);
        return new ResponseEntity<BookEntity>(savedBookEntity , HttpStatus.CREATED);
    }


    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookEntity> find(@PathVariable final Long id){
        final Optional<BookEntity> foundBookEntity = bookService.find(id);
       return foundBookEntity.map(book -> new ResponseEntity<BookEntity>(book , HttpStatus.OK))
                .orElse(new ResponseEntity<BookEntity>(HttpStatus.NOT_FOUND));
    }



    @GetMapping("/api/books")
    public ResponseEntity<List<BookEntity>> list(){
        return new ResponseEntity<List<BookEntity>>(bookService.listBooks() , HttpStatus.OK);
    }


    @PutMapping("/api/books/{id}")
    public ResponseEntity<BookEntity> update(
            @RequestBody final BookEntity bookEntity,
            @PathVariable Long id){

        if(!bookService.isBookExists(id)){
            return new ResponseEntity<BookEntity>(bookEntity , HttpStatus.NOT_FOUND);
        }


        bookEntity.setID(id);
        BookEntity savedBookEntity = bookService.create(bookEntity);
        return new ResponseEntity<BookEntity>(savedBookEntity , HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        if (!bookService.isBookExists(id)){
            return new ResponseEntity<Boolean>(false , HttpStatus.NOT_FOUND);
        }

        bookService.deleteById(id);

        return new ResponseEntity<Boolean>(true , HttpStatus.OK);

    }





}
