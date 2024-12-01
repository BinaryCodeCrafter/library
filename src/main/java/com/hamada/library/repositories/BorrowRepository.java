package com.hamada.library.repositories;

import com.hamada.library.domain.BookEntity;
import com.hamada.library.domain.BorrowingRecordEntity;
import com.hamada.library.domain.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<BorrowingRecordEntity , Long> {

    Optional<BorrowingRecordEntity> findByBookAndPatronAndReturnDateIsNull(BookEntity book, PatronEntity patron);

    Optional<BorrowingRecordEntity> findByBookAndReturnDateIsNull(BookEntity book);

}
