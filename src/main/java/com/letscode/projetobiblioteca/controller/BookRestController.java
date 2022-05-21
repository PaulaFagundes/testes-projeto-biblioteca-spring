package com.letscode.projetobiblioteca.controller;

import com.letscode.projetobiblioteca.dto.BookDto;
import com.letscode.projetobiblioteca.model.Book;
import com.letscode.projetobiblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/book-rest")
@RestController
public class BookRestController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> listaTodos() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid BookDto bookDto) {

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAutor(bookDto.getAutor());

        bookService.saveBook(book);

    }
}
