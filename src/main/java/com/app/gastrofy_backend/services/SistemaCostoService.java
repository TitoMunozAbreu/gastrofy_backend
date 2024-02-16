package com.app.gastrofy_backend.services;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.model.Usuario;

public interface SistemaCostoService {
    void registrarSistemaCosto(String s, Usuario usuario) throws DuplicateResourceException;
}
