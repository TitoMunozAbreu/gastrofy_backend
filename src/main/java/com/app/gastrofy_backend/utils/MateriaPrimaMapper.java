package com.app.gastrofy_backend.utils;

import com.app.gastrofy_backend.model.MateriaPrima;
import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.enums.Categoria;
import com.app.gastrofy_backend.model.enums.Presentacion;
import com.app.gastrofy_backend.model.request.MateriaPrimaRequest;
import com.app.gastrofy_backend.model.response.MateriaPrimaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.gastrofy_backend.model.enums.Presentacion.valueOf;

@Component
@Slf4j
public class MateriaPrimaMapper {
    public static List<MateriaPrima> mapListMateriaPrimaRequestToEntidad(List<MateriaPrimaRequest> materiaPrimaRequests, SistemaCosto sistemaCosto){
            log.info("Mapping '{}' materias primas para sistema costo con ID '{}'", materiaPrimaRequests.size(), sistemaCosto.getIdSistemaCosto());
            return materiaPrimaRequests.stream()
                    .map(materiaPrimaRequest -> {
                        return  mapMateriaPrimaRequestToEntidad(materiaPrimaRequest,sistemaCosto);
                    })
                    .collect(Collectors.toList());
    }

    public static MateriaPrima mapMateriaPrimaRequestToEntidad(MateriaPrimaRequest materiaPrimaRequest, SistemaCosto sistemaCosto){
        log.info("Mapping materia prima '{}'", materiaPrimaRequest.nombre());
        //almacenar en variables
        double precioCompra = materiaPrimaRequest.precioCompra();
        double cantidad = materiaPrimaRequest.cantidad();
        Presentacion presentacion = valueOf(materiaPrimaRequest.presentacion());
        //incializar objeto que realizar los calculos de costos
        CalcularCostos calcularCostos = new CalcularCostos(precioCompra,cantidad,presentacion);
        return MateriaPrima.builder()
                .codigoProducto(materiaPrimaRequest.codigoProducto())
                .nombre(materiaPrimaRequest.nombre())
                .precioCompra(precioCompra)
                .cantidad(cantidad)
                .presentacion(presentacion)
                .proveedor(materiaPrimaRequest.proveedor())
                .categoria(Categoria.valueOf(materiaPrimaRequest.categoria()))
                .costoUnitarioKg(calcularCostos.calcularCostoUnitarioKg())
                .costoUnitarioGr(calcularCostos.calcularCostoUnitarioGr())
                .costoUnitarioLb(calcularCostos.calcularCostoUnitarioLb())
                .costoUnitarioOz(calcularCostos.calcularCostoUnitarioOz())
                .sistemaCosto(sistemaCosto)
                .build();
    }

    public static List<MateriaPrimaResponse> mapListEntidadToMateriaPrimaResponse(List<MateriaPrima> materiaPrimas){
        log.info("Mapping '{}' materias primas response", materiaPrimas.size());
        return materiaPrimas.stream()
                .map(MateriaPrimaMapper::mapEntidadToMateriaPrimaResponse)
                .collect(Collectors.toList());
    }

    public static MateriaPrimaResponse mapEntidadToMateriaPrimaResponse(MateriaPrima materiaPrima){
        return MateriaPrimaResponse.builder()
                .codigoProducto(materiaPrima.getCodigoProducto())
                .nombre(materiaPrima.getNombre())
                .precioCompra(materiaPrima.getPrecioCompra())
                .cantidad(materiaPrima.getCantidad())
                .presentacion(materiaPrima.getPresentacion())
                .costoUnitarioKg(materiaPrima.getCostoUnitarioKg())
                .costoUnitarioGr(materiaPrima.getCostoUnitarioGr())
                .costoUnitarioLb(materiaPrima.getCostoUnitarioLb())
                .costoUnitarioOz(materiaPrima.getCostoUnitarioOz())
                .proveedor(materiaPrima.getProveedor())
                .categoria(materiaPrima.getCategoria())
                .build();
    }

}
