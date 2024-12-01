package com.hamada.library.services;

import com.hamada.library.domain.BorrowingRecordEntity;

public interface BorrowService {
    BorrowingRecordEntity borrowBook(Long bookId , Long patronID);
    BorrowingRecordEntity returnBook(Long bookId , Long patronId);
}
