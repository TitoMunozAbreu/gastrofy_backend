package com.app.gastrofy_backend.model;

import com.app.gastrofy_backend.model.enums.Presentacion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
@Table
public class SemiElaborado {
    @Id
    @SequenceGenerator(
            name = "semi_elaborado_sequence",
            sequenceName = "semi_elaborado_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "semi_elaborado_sequence"
    )
    private Integer idSemiElaborado;

    private String nombre;

    private Presentacion presentacion;

    private double costoReceta;

    private String cantidadResultante;

    private double merma;

    private double costoFinal;

    private double costoTotalPresentacion;

    @ManyToOne
    @JoinColumn(
            name = "id_sistema_costo",
            referencedColumnName = "idSistemaCosto"

    )
    private SistemaCosto sistemaCosto;

    @ManyToMany
    @JoinTable(
            name = "semi_elaborado_materia_prima",
            joinColumns = {
                    @JoinColumn(name = "id_semi_elaborado", referencedColumnName = "idSemiElaborado")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_materia_prima", referencedColumnName = "idMateriaPrima")}
    )
    private List<MateriaPrima> materiaPrimas;

    @ManyToMany
    @JoinTable(
            name = "semi_elaborado_compuesto",
            joinColumns = {
                    @JoinColumn(name = "id_semi_elaborado", referencedColumnName = "idSemiElaborado")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_compuesto_semi_elaborado", referencedColumnName = "idSemiElaborado")}
    )
    private List<SemiElaborado> semiElaborados;
}
