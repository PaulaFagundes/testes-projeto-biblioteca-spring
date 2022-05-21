package com.letscode.projetobiblioteca.testesUnitariosRestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.projetobiblioteca.controller.BookRestController;
import com.letscode.projetobiblioteca.dto.BookDto;
import com.letscode.projetobiblioteca.model.Book;
import com.letscode.projetobiblioteca.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookRestController.class)
public class BookRestControllerTest {
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void listAllBookControllerTest() throws Exception {

        List<Book> booksList = new ArrayList<>();
        booksList.add(new Book(1L, "Book 1", "Teste"));
        booksList.add(new Book(2L, "Book 2", "Teste"));
        booksList.add(new Book(3L, "Book 3", "Teste"));

        Mockito.when(bookService.getAll())
                .thenReturn(booksList);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/book-rest")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(booksList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(booksList.get(0).getId()), Long.class));
    }

    @Test
    @WithMockUser
    void saveBookTestController() throws Exception {

        BookDto bookDto = new BookDto();
        bookDto.setName("Book Teste");
        bookDto.setAutor("Teste");

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAutor(bookDto.getAutor());

        Mockito.when(bookService.saveBook(book))
                .thenReturn(book);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/book-rest/save")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(bookDto))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getAutor(), book.getAutor());
    }
}
