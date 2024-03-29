package com.app.gastrofy_backend.services.impl;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.Usuario;
import com.app.gastrofy_backend.repositories.SistemaCostoRepository;
import com.app.gastrofy_backend.services.SistemaCostoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.gastrofy_backend.model.enums.GlobalPresentacion.KG;

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
        log.info("Registrar sistema de costo para la empresa {}", nombreEmpresa);
        SistemaCosto sistemaCosto = SistemaCosto.builder()
                .nombreEmpresa(nombreEmpresa)
                .usuario(usuario)
                //Estableciendo por defecto globalPresentacion
                .globalPresentacion(KG)
                .build();
        //persistir sistema de costo
        sistemaCostoRepository.save(sistemaCosto);
        return sistemaCosto;
    }

    @Override
    public void eliminarSistemaCostoPorNombre(String nombreEmpresa) throws ResourceNotFoundException {
        log.info("Eliminar sistema de costo empresa {}", nombreEmpresa);
        SistemaCosto sistemaCosto = sistemaCostoRepository.findByNombreEmpresa(nombreEmpresa)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa '%s' NO se encuentra registrada"
                        .formatted(nombreEmpresa)));

        //TODO: Eliminar las tablas asociadas al sistema una a una.
        //eliminar sistema de la BBDD
        sistemaCostoRepository.delete(sistemaCosto);
    }

    @Override
    public SistemaCosto obtenerSistemaCostoPorID(Integer sistemaID) throws ResourceNotFoundException {
        log.info("Obtener sistema costo con ID {}", sistemaID);
        return sistemaCostoRepository.findById(sistemaID)
                .orElseThrow(() -> new ResourceNotFoundException("Sistema costo ID '%s' NO se encuentra registrada"
                .formatted(sistemaID)));
    }
}
