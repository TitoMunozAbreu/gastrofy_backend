package com.app.gastrofy_backend.repositories;

import com.app.gastrofy_backend.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    //comprobar si existe usuario registrado con email
    Boolean existsByEmail(String email);
    // listar usuarios
    Page<Usuario> findByNombreContaining(String nombre, Pageable pageable);
}
