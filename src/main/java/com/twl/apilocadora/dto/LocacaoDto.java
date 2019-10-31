package com.twl.apilocadora.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LocacaoDto {

    @NotNull
    private List<Long> idsFilme;
    @NotNull
    private Long idUsuario;
}
