package com.jwd.developmentbooks;

import java.math.BigDecimal;
import java.util.List;

public class BookService {
    private static final BigDecimal PRICE = new BigDecimal("50.00");

    private static final List<Book> BOOKS = List.of(
            new Book("9780132350884", "Clean Code", "Robert C. Martin", 2008, PRICE),
            new Book("9780137081073", "The Clean Coder", "Robert C. Martin", 2011, PRICE),
            new Book("9780134494166", "Clean Architecture", "Robert C. Martin", 2017, PRICE),
            new Book("9780134494166", "Test Driven Development by Example", "Kent Beck", 2003, PRICE),
            new Book("9780131177055", "Working Effectively with Legacy Code", "Michael C. Feathers", 2004, PRICE)
    );

    public List<Book> getAllBooks() {
        return BOOKS;
    }

    public Book getBookByIsbn(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN cannot be null");
        }
        return BOOKS.stream()
                .filter(b -> b.isbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
}