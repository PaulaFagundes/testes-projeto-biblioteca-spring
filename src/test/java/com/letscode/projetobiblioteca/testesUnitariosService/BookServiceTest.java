package com.letscode.projetobiblioteca.testesUnitariosService;

import com.letscode.projetobiblioteca.model.Book;
import com.letscode.projetobiblioteca.repository.BookRepository;
import com.letscode.projetobiblioteca.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @Test
    void saveBookTest() {
        Book bookSave = new Book();
        bookSave.setName("LivroTeste");
        bookSave.setAutor("Teste");

        Book bookReturn = new Book();
        bookReturn.setId(1234L);
        bookReturn.setName("LivroTeste");
        bookReturn.setAutor("Teste");

        Mockito.when(bookRepository.save(bookSave))
                .thenReturn(bookReturn);

        bookReturn = bookService.saveBook(bookSave);


        Assertions.assertNotNull(bookReturn);
        Assertions.assertNotNull(bookReturn.getId());
        Assertions.assertEquals(1234L, bookReturn.getId());
        Assertions.assertEquals(bookSave.getName(), bookReturn.getName());
        Assertions.assertEquals(bookSave.getAutor(), bookReturn.getAutor());
    }


    @Test
    void getAllBookTest() {

        List<Book> booksList = new ArrayList<>();
        booksList.add(new Book(1L,"Livro 1", "Teste"));
        booksList.add(new Book(2L, "Livro 2","Teste"));
        booksList.add(new Book(3L, "Livro3","Teste"));

        Mockito.when(bookRepository.findAll())
                .thenReturn(booksList);

        List<Book> books = bookService.getAll();

        Assertions.assertNotNull(books);
        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(3, booksList.size());

    }
}
