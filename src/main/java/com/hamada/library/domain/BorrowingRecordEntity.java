package com.hamada.library.domain;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing-records")
public class BorrowingRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;


    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private PatronEntity patron;

    private LocalDate borrowedDate;

    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public PatronEntity getPatron() {
        return patron;
    }

    public void setPatron(PatronEntity patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowingRecordEntity(){}

    public BorrowingRecordEntity(Long id, BookEntity book, PatronEntity patron, LocalDate borrowedDate, LocalDate returnDate) {
        this.id = id;
        this.book = book;
        this.patron = patron;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }
}
