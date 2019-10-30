package com.twl.apilocadora.filter;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmeFilter {

    private Long idFilme;
    private String titulo;
    private String autor;
}
