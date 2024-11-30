package com.hamada.library.services;

import com.hamada.library.domain.BookEntity;
import com.hamada.library.domain.PatronEntity;

import java.util.List;
import java.util.Optional;

public interface PatronService {

    PatronEntity create(PatronEntity patron);

    Optional<PatronEntity> find(Long ID);

    List<PatronEntity> listPatrons();

    Boolean isPatronExists(Long id);

    void deleteById(Long id);

}
