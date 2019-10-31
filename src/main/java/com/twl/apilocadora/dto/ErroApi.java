package com.twl.apilocadora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroApi {

    private String mensagemErro;
    private String tipoErro;
    private String stackTrace;
}
