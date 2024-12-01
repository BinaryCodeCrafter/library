package com.hamada.library.services.impl;

import com.hamada.library.domain.BookEntity;
import com.hamada.library.domain.BorrowingRecordEntity;
import com.hamada.library.domain.PatronEntity;
import com.hamada.library.repositories.BookRepository;
import com.hamada.library.repositories.BorrowRepository;
import com.hamada.library.repositories.PatronRepository;
import com.hamada.library.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationAotProcessor;
import org.springframework.boot.SpringBootVersion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class BorrowServiceImpl implements BorrowService {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowServiceImpl(BorrowRepository borrowRepository,
                                  BookRepository bookRepository,
                                  PatronRepository patronRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }


    @Override
    @Transactional
    public BorrowingRecordEntity borrowBook(Long bookId, Long patronID) {

        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Boot not found")
        );

        PatronEntity patronEntity = patronRepository.findById(patronID).orElseThrow(
                () -> new RuntimeException("Patron not found")
        );

        Optional<BorrowingRecordEntity> record = borrowRepository.findByBookAndReturnDateIsNull(bookEntity);


        if (record.isPresent()) {
            throw new RuntimeException("Book is already borrowed");
        }


        BorrowingRecordEntity borrowingRecordEntity = new BorrowingRecordEntity();
        borrowingRecordEntity.setBook(bookEntity);
        borrowingRecordEntity.setPatron(patronEntity);
        borrowingRecordEntity.setBorrowedDate(LocalDate.now());
        borrowingRecordEntity.setReturnDate(null);

        return borrowRepository.save(borrowingRecordEntity);

    }

    @Override
    @Transactional
    public BorrowingRecordEntity returnBook(Long bookId, Long patronId) {
        Optional<BorrowingRecordEntity> recordOptional = borrowRepository.findByBookAndPatronAndReturnDateIsNull(
                bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found")),
                patronRepository.findById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"))
        );

        if (recordOptional.isEmpty()) {
            throw new RuntimeException("No borrowing record found for this book and patron");
        }

        BorrowingRecordEntity record = recordOptional.get();

        record.setReturnDate(LocalDate.now());

        return borrowRepository.save(record);


    }
}
