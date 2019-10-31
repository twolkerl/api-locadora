package com.twl.apilocadora.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank(message = "O campo email é obrigatório!")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório!")
    private String senha;
}
