package com.twl.apilocadora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TA_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = -1541316898695066195L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @NotBlank
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotBlank
    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @NotBlank
    @Column(name = "SENHA")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
}
