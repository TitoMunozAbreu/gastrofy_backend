package com.app.gastrofy_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueFecha",
                        columnNames = {"fecha"})
        }
)
public class Venta {

    @Id
    @SequenceGenerator(
            name = "venta_sequence",
            sequenceName = "venta_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "venta_sequence"
    )
    private String idVenta;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String,Double> ventaDetalle;

    private LocalDate fecha;

    private int transaccion;

    private double ticketPromedio;

    @ManyToOne
    @JoinColumn(
            name = "id_sede",
            referencedColumnName = "idSede"
    )
    private Sede sede;
}
