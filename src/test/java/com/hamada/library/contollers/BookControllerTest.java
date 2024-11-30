package com.hamada.library.contollers;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hamada.library.testData;
//
//import com.hamada.library.domain.BookEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.assertj.MockMvcTester;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class BookControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testThatBookIsCreated() throws Exception {
//        final BookEntity bookEntity = testData.testBookEntity();
//        final ObjectMapper objectMapper = new ObjectMapper();
//        final String bookJson = objectMapper.writeValueAsString(bookEntity);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/books")
//                        .contentType("application/json")
//                        .content(bookJson))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().json(bookJson));
//    }
//
//
//
//}
