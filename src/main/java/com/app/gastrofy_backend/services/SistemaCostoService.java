package com.app.gastrofy_backend.services;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.Usuario;

public interface SistemaCostoService {
    SistemaCosto registrarSistemaCosto(String s, Usuario usuario) throws DuplicateResourceException;

    void eliminarSistemaCostoPorNombre(String nombreEmpresa) throws ResourceNotFoundException;
}
