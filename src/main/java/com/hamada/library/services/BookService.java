package com.hamada.library.services;


import com.hamada.library.domain.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity create(BookEntity book);

    Optional<BookEntity> find(Long ID);

    List<BookEntity> listBooks();

    Boolean isBookExists(Long id);

    void deleteById(Long id);

}
