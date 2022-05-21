package com.letscode.projetobiblioteca.testesIntegracaoService;

import com.letscode.projetobiblioteca.model.Book;
import com.letscode.projetobiblioteca.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceIntegTest {
    @Autowired
    BookService bookService;

    Book bookTest;

    @BeforeAll
    static void iniciaTestesAll() {
        System.out.println("iniciaTestesAll");
    }

    @BeforeEach
    void iniciaTestesEach() {
        System.out.println("iniciaTestesEach");
        bookTest = new Book();
        bookTest.setName("Book teste 1");
        bookTest.setAutor("Teste");
    }

    @Test
    @Transactional
    void saveBookTestInteg() {
        Book bookSave = bookService.saveBook(bookTest);

        assertNotNull(bookSave.getId());
        assertEquals(bookTest.getName(), bookSave.getName());
    }

    @Test
    void getAllBookTest() {

        List<Book> books = bookService.getAll();

        assertNotNull(books);
        assertTrue(books.size() > 0);

    }

    @Test
    void saveBookAndGetAll() {

        Book bookReturn = bookService.saveBook(bookTest);

        List<Book> booksList = bookService.getAll();

        assertTrue(booksList.contains(bookReturn));

        AtomicReference<Book> bookReturnList = new AtomicReference<>();
        booksList.forEach(book -> {
            if (book.equals(bookReturn)) {
                bookReturnList.set(book);
            }
        });

        assertNotNull(bookReturnList.get());
        assertEquals(bookReturn.getName(), bookReturnList.get().getName());

    }
}
