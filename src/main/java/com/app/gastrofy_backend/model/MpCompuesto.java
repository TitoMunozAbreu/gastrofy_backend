package com.app.gastrofy_backend.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
@Table
public class MpCompuesto {
    @Id
    @SequenceGenerator(
            name = "mp_compuesto_sequence",
            sequenceName = "mp_compuesto_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "mp_compuesto_sequence"
    )
    private Integer idMpCompuesto;

    private double cantidad;

    private double costo;

    private double total;

    @ManyToOne
    @JoinColumn(
            name = "id_materia_prima",
            referencedColumnName = "idMateriaPrima"
    )
    private MateriaPrima materiaPrima;

    @ManyToOne
    @JoinColumn(
            name = "id_semi_elaborado",
            referencedColumnName = "idSemiElaborado"
    )
    private SemiElaborado semiElaborado;
}
