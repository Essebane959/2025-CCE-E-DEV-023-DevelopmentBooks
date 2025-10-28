package com.jwd.developmentbooks.web;

import com.jwd.developmentbooks.model.Book;
import com.jwd.developmentbooks.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService books;

    public BookController(BookService books) {
        this.books = books;
    }

    @GetMapping
    public ResponseEntity<List<Book>> all() {
        return ResponseEntity.ok(books.getAllBooks());
    }
}