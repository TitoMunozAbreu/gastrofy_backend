package com.app.gastrofy_backend.model;

import com.app.gastrofy_backend.model.enums.Clasificacion;
import com.app.gastrofy_backend.model.enums.Periodo;
import com.app.gastrofy_backend.model.enums.TipoCosto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
@Table
public class Costo {

    @Id
    @SequenceGenerator(
            name = "costo_sequence",
            sequenceName = "costo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "costo_sequence"
    )
    private Integer idCosto;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String,Double> costoDetalle;

    private TipoCosto tipoCosto;

    private Periodo periodo;

    private Clasificacion clasificacion;

    @ManyToOne
    @JoinColumn(
            name = "id_sede",
            referencedColumnName = "idSede"
    )
    private Sede sede;
}
