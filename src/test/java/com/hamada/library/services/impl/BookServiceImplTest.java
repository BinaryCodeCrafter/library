//package com.hamada.library.services.impl;
//
//import com.hamada.library.domain.BookEntity;
//import com.hamada.library.repositories.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static com.hamada.library.testData.testBookEntity;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceImplTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @InjectMocks
//    private BookServiceImpl underTest;
//
//    @Test
//    public void testThatBookIsSaved(){
//        final BookEntity bookEntity = testBookEntity();
//
//        final BookEntity bookEntityReturned =  testBookEntity();
//
//        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntityReturned);
//
//        final BookEntity result = underTest.create(bookEntity);
//
//        assertEquals(bookEntity , result);
//
//    }
//}
