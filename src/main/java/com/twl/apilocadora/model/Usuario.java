package com.twl.apilocadora.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TA_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = -1541316898695066195L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @NotBlank(message = "É necessário informar o campo email!")
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotBlank(message = "É necessário informar o campo nomeCompleto!")
    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @NotBlank(message = "É necessário informar o campo senha!")
    @Column(name = "SENHA")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
}
