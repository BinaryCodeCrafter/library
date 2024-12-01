package com.hamada.library.domain;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;

    @NotEmpty(message = "Title is required")
    private String title;


    @NotEmpty(message = "Author is required")
    private String author;

    @NotEmpty(message = "ISBN is required")
    private String ISBN;

    @NotNull(message = "Publication Year is required")
    private Integer publicationYear;

    @NotEmpty(message = "Genre is required")
    private String genre;

    public BookEntity(Long ID, String title, String author, String ISBN, Integer publicationYear, String genre) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public BookEntity(){

    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
