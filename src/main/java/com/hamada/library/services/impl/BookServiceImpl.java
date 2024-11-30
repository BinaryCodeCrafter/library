package com.hamada.library.services.impl;

import com.hamada.library.domain.BookEntity;
import com.hamada.library.repositories.BookRepository;
import com.hamada.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity create(final BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    @Override
    public Optional<BookEntity> find(Long ID) {
        return bookRepository.findById(ID);
    }

    @Override
    public List<BookEntity> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Boolean isBookExists(Long ID) {
        return bookRepository.existsById(ID);
    }

    @Override
    public void deleteById(Long ID) {
        bookRepository.deleteById(ID);
    }

}
