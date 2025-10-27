package com.jwd.developmentbooks.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookServiceTest {

    private final BookService service = new BookService();

    @Test
    void givenFiveBooks_whenGetAllBooks_thenReturnFive() {
        List<Book> all = service.getAllBooks();
        assertNotNull(all);
        assertEquals(5, all.size());
    }

    @Test
    void givenExistingIsbn_whenGetBookByIsbn_thenReturnBook() {
        Book book = service.getBookByIsbn("1234567891452");
        assertNotNull(book);
        assertEquals("Clean Code", book.title());
    }

    @Test
    void givenUnknownIsbn_whenGetBookByIsbn_thenReturnNull() {
        Book book = service.getBookByIsbn("0000000000000");
        assertNull(book);
    }
}