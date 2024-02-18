package com.app.gastrofy_backend.services.impl;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.Usuario;
import com.app.gastrofy_backend.repositories.SistemaCostoRepository;
import com.app.gastrofy_backend.services.SistemaCostoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {DuplicateResourceException.class})
@Slf4j
public class SistemaCostoServiceImpl implements SistemaCostoService {

    private final SistemaCostoRepository sistemaCostoRepository;

    public SistemaCostoServiceImpl(SistemaCostoRepository sistemaCostoRepository) {
        this.sistemaCostoRepository = sistemaCostoRepository;
    }

    @Override
    public SistemaCosto registrarSistemaCosto(String nombreEmpresa, Usuario usuario) throws DuplicateResourceException {
        log.info("Comprobar si se encuentra registrada empresa {}", nombreEmpresa);
        Boolean existsPorNombreEmpresa = sistemaCostoRepository.existsByNombreEmpresa(nombreEmpresa);
        //comprobar si el nombre se encuentra registrado
        if(existsPorNombreEmpresa){
            throw new DuplicateResourceException("Empresa '%s' se encuentra registrada"
                    .formatted(nombreEmpresa));
        }
        log.info("Creando sistema de costo de la empresa {}", nombreEmpresa);
        SistemaCosto sistemaCosto = SistemaCosto.builder()
                .nombreEmpresa(nombreEmpresa)
                .usuario(usuario)
                .build();
        //persistir sistema de costo
        sistemaCostoRepository.save(sistemaCosto);
        return sistemaCosto;
    }
}
