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

    @Enumerated(EnumType.STRING)
    private TipoCosto tipoCosto;

    @Enumerated(EnumType.STRING)
    private Periodo periodo;

    @Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;

    @ManyToOne
    @JoinColumn(
            name = "id_venta",
            referencedColumnName = "idVenta"
    )
    private Venta venta;
}
