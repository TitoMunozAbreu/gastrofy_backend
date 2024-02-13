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
                        name = "unique_nombre_sede",
                        columnNames = {"nombreSede"}
                )
        }
)
public class Sede {

    @Id
    @SequenceGenerator(
            name = "sede_sequence",
            sequenceName = "sede_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "sede_sequence"
    )
    private Integer idSede;

    private String nombreSede;

    @ManyToOne
    @JoinColumn(
            name = "id_sistema_costo",
            referencedColumnName = "idSistemaCosto",
            unique = true
    )
    private SistemaCosto sistemaCosto;
}
