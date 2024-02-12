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
                        name = "UniqueEmail",
                        columnNames = {"email"})
        }
)
public class Usuario {

    @Id
    @SequenceGenerator(
            name = "usuario_sequence",
            sequenceName = "usuario_sequence"
            ,allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "usuario_sequence"
    )
    private Integer idUsuario;

    private String email;

    private String nombre;

    private String apellido;

    private String contrasena;

    private String movil;

    private String rol;

}
