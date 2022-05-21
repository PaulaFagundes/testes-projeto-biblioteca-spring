package com.letscode.projetobiblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BookDto {
    @NotEmpty(message = "Nome é obrigatorio")
    private String name;

    @NotEmpty(message = "Autor é obrigatorio")
    private String autor;

}

