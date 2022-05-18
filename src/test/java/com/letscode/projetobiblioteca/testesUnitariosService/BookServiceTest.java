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
        bookSave.setYearOfPublication(2022);
        bookSave.setQuantity(6);

        Book bookRetorno = new Book();
        bookRetorno.setName("LivroTeste");
        bookRetorno.setYearOfPublication(2022);
        bookRetorno.setQuantity(6);

        Mockito.when(bookRepository.save(bookSave))
                .thenReturn(bookRetorno);

        bookRetorno = bookService.saveBook(bookSave);

        Assertions.assertNotNull(bookRetorno);
        Assertions.assertEquals(bookSave.getName(), bookRetorno.getName());
        Assertions.assertEquals(bookSave.getYearOfPublication(), bookRetorno.getYearOfPublication());
        Assertions.assertEquals(bookSave.getQuantity(), bookRetorno.getQuantity());
    }


    @Test
    void listaClienteTeste() {

        List<Cliente> clientesList = new ArrayList<>();
        clientesList.add(new Cliente(1L, "Cliente 1", "123", "cliente1@test.com"));
        clientesList.add(new Cliente(2L, "Cliente 2", "456", "cliente2@test.com"));
        clientesList.add(new Cliente(3L, "Cliente 3", "54656", "cliente3@test.com"));

        Mockito.when(clienteRepository.findAll())
                .thenReturn(clientesList);

        List<Cliente> clientes = clienteService.lista();

        Assertions.assertNotNull(clientes);
        Assertions.assertFalse(clientes.isEmpty());
        Assertions.assertEquals(3, clientesList.size());

    }
}
