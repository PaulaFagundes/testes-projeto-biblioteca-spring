package com.letscode.projetobiblioteca.testesIntegracaoRestController;

import com.letscode.projetobiblioteca.dto.BookDto;
import com.letscode.projetobiblioteca.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRestControllerIntegTest {
    @Autowired
    TestRestTemplate restTemplate;
    static String username;
    static String password;

    @BeforeAll
    static void iniciaTestes() {
        username = "admin";
        password = "1234";
    }

    @Test
    void saveBookControllerIntTeste() {

        BookDto bookDto = new BookDto();
        bookDto.setName("Nome livro teste");
        bookDto.setAutor("Teste");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(bookDto, httpHeaders);

        ResponseEntity response = this.restTemplate
                .withBasicAuth(username, password)
                .exchange("/book-rest/save", HttpMethod.POST, httpEntity, Void.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void listBookControllerIntegTeste() {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<Book[]> response = this.restTemplate
                .withBasicAuth(username, password)
                .exchange("/book-rest", HttpMethod.GET, httpEntity, Book[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotEquals(0, response.getBody().length);
        Assertions.assertNotNull(response.getBody()[0].getName());

    }
}
