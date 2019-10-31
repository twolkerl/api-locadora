package com.twl.apilocadora.filter;

import lombok.Data;

@Data
public class UsuarioFilter {

    private Long idUsuario;
    private String nomeCompleto;
    private String email;
}
