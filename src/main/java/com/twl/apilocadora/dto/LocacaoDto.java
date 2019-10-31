package com.twl.apilocadora.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LocacaoDto {

    @NotNull
    private Long idFilme;
    @NotNull
    private Long idUsuario;
}
