package com.hamada.library.controllers;


import com.hamada.library.domain.BorrowingRecordEntity;
import com.hamada.library.services.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@Validated
public class BorrowController {
    private final BorrowService borrowService;


    @Autowired
    public BorrowController(BorrowService borrowService){
        this.borrowService = borrowService;
    }


    @PostMapping("api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordEntity> borrowBook(@PathVariable @Valid Long bookId , @PathVariable @Valid Long patronId){
        try {
            BorrowingRecordEntity record = borrowService.borrowBook(bookId , patronId);
            return new ResponseEntity<BorrowingRecordEntity>(record , HttpStatus.CREATED);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordEntity> returnBook(@PathVariable @Valid Long bookId , @PathVariable @Valid Long patronId){
        try{
            BorrowingRecordEntity record =  borrowService.returnBook(bookId , patronId);
            return new ResponseEntity<BorrowingRecordEntity>(record , HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}