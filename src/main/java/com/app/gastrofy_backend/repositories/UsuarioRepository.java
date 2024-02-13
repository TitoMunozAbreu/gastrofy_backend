package com.app.gastrofy_backend.repositories;

import com.app.gastrofy_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
