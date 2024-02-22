package com.app.gastrofy_backend.repositories;

import com.app.gastrofy_backend.model.SistemaCosto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SistemaCostoRepository extends JpaRepository<SistemaCosto, Integer> {
    //comprobar si existe sistema registrado con email
    Boolean existsByNombreEmpresa(String nombreEmpresa);

    //encontrar sistema por nombre
    Optional<SistemaCosto> findByNombreEmpresa(String nombreEmpresa);
}
