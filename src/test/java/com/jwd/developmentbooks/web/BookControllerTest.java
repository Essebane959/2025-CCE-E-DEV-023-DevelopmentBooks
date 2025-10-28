package com.jwd.developmentbooks.web;

import com.jwd.developmentbooks.model.Book;
import com.jwd.developmentbooks.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {

    private MockMvc mockMvc;
    private AutoCloseable mocks;

    @Mock
    private BookService bookService;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
        BookController controller = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void getAllBooks_returnsFive() throws Exception {
        BigDecimal price = new BigDecimal("50.00");
        when(bookService.getAllBooks()).thenReturn(List.of(
                new Book("9780132350884","Clean Code","Robert C. Martin",2008, price),
                new Book("9780137081073","The Clean Coder","Robert C. Martin",2011, price),
                new Book("9780134494166","Clean Architecture","Robert C. Martin",2017, price),
                new Book("9780321146533","Test Driven Development by Example","Kent Beck",2003, price),
                new Book("9780131177055","Working Effectively with Legacy Code","Michael C. Feathers",2004, price)
        ));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].isbn").value("9780132350884"));
    }
}