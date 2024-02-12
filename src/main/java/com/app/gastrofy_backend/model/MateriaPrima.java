package com.app.gastrofy_backend.model;

import com.app.gastrofy_backend.model.enums.Categoria;
import com.app.gastrofy_backend.model.enums.Presentacion;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
@Table
public class MateriaPrima {

    @Id
    @SequenceGenerator(
            name = "materia_prima_sequence",
            sequenceName = "materia_prima_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "materia_prima_sequence"
    )
    private Integer idMateriaPrima;

    private String nombre;

    private double precioCompra;

    private double cantidad;

    private Presentacion presentacion;

    private double costoUnitario;

    private String proveedor;

    private Categoria categoria;
}
