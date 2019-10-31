package com.twl.apilocadora.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"filme", "usuario"})
@EqualsAndHashCode(exclude = {"filme", "usuario"})
@JsonIgnoreProperties({"filme", "usuario"})
@Entity
@Table(name = "TA_INVENTARIO_FILME")
public class InventarioFilme implements Serializable {

    private static final long serialVersionUID = -8589587885574602868L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVENTARIO_FILME")
    private Long idInventarioFilme;

    @NotNull
    @Column(name = "ID_FILME")
    private Long idFilme;

    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @JoinColumn(name = "ID_FILME", referencedColumnName = "ID_FILME", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Filme filme;

    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
