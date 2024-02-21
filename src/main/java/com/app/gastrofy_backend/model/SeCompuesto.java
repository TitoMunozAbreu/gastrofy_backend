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
public class SeCompuesto {
    @Id
    @SequenceGenerator(
            name = "se_compuesto_sequence",
            sequenceName = "se_compuesto_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "se_compuesto_sequence"
    )
    private Integer idSeCompuesto;

    private double cantidad;

    private double costo;

    private double total;

    @ManyToOne
    @JoinColumn(
            name = "id_semi_elaborado",
            referencedColumnName = "idSemiElaborado"
    )
    private SemiElaborado semiElaborado;
}
