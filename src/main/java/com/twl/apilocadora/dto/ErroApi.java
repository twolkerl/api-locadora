package com.twl.apilocadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErroApi {

    private List<String> mensagemErro;
    private String tipoErro;
    private String stackTrace;
}
