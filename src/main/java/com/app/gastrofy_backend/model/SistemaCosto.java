package com.app.gastrofy_backend.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_nombre_empresa",
                        columnNames = {"nombreEmpresa"}
                )
        }
)
public class SistemaCosto {

    @Id
    @SequenceGenerator(
            name = "sistema_costo_sequence",
            sequenceName = "sistema_costo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "sistema_costo_sequence"
    )
    private Integer idSistemaCosto;

    private String nombreEmpresa;

    @OneToOne
    @JoinColumn(
            name = "id_usuario",
            referencedColumnName = "idUsuario"
    )
    private Usuario usuario;

}
