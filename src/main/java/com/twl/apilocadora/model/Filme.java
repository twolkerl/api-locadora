package com.twl.apilocadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TA_FILME")
public class Filme implements Serializable {

    private static final long serialVersionUID = 8794135598611083775L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FILME")
    private Long idFilme;

    @NotBlank
    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRETOR")
    private String diretor;
}
