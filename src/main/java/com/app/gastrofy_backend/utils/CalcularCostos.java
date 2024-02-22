package com.app.gastrofy_backend.utils;

import com.app.gastrofy_backend.model.enums.Presentacion;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CalcularCostos {
    private final double PRECIO_COMPRA;
    private final double CANTIDAD;
    private final Presentacion PRESENTACION;

    public CalcularCostos(double PRECIO_COMPRA, double CANTIDAD, Presentacion PRESENTACION) {
        this.PRECIO_COMPRA = PRECIO_COMPRA;
        this.CANTIDAD = CANTIDAD;
        this.PRESENTACION = PRESENTACION;
    }

    public double calcularCostoUnitarioKg(){
        double costo = calcularCosto();
        log.info("Calcular costo unitario a kg");
        double costoUnitario = 0;
        //tabla de conversion a GlobalPresentacion (KG)
        switch (PRESENTACION){
            //calcular costo unitario segun presentacion
            case KG, UND, LT -> costoUnitario = costo * 1;
            case GR -> costoUnitario = costo * 1000;
            case LB -> costoUnitario = costo * 2.20462;
            case OZ -> costoUnitario = costo / 0.02835;
            case GAL -> costoUnitario = costo / 3.78541;
        }
        return costoUnitario;
    }

    public double calcularCostoUnitarioGr(){
        double costo = calcularCosto();
        log.info("Calcular costo unitario a Gr");
        double costoUnitario = 0;
        //tabla de conversion a GlobalPresentacion (GR)
        switch (PRESENTACION){
            //calcular costo unitario segun presentacion
            case KG, LT -> costoUnitario = costo / 1000;
            case GR -> costoUnitario = costo / 1;
            case LB -> costoUnitario = costo / 453.6;
            case OZ -> costoUnitario = costo / 28.35;
            case GAL -> costoUnitario = costo / 3785.41180;
            case UND -> costoUnitario = costo * 1;
        }
        return costoUnitario;
    }

    public double calcularCostoUnitarioLb(){
        double costo = calcularCosto();
        log.info("Calcular costo unitario a Lb");
        double costoUnitario = 0;
        //tabla de conversion a GlobalPresentacion (LB)
        switch (PRESENTACION){
            //calcular costo unitario segun presentacion
            case KG, LT -> costoUnitario = costo / 2.20462;
            case GR -> costoUnitario = costo * 453.59230;
            case LB -> costoUnitario = costo / 1;
            case OZ -> costoUnitario = costo * 16;
            case GAL -> costoUnitario = costo / 8.34540;
            case UND -> costoUnitario = costo * 1;
        }
        return costoUnitario;
    }

    public double calcularCostoUnitarioOz(){
        double costo = calcularCosto();
        log.info("Calcular costo unitario a Oz");
        double costoUnitario = 0;
        //tabla de conversion a GlobalPresentacion (OZ)
        switch (PRESENTACION){
            //calcular costo unitario segun presentacion
            case KG, LT -> costoUnitario = costo / 35.27396;
            case GR -> costoUnitario = costo / 0.03527;
            case LB -> costoUnitario = costo / 16;
            case OZ -> costoUnitario = costo * 1;
            case GAL -> costoUnitario = costo / 133.52647;
            case UND -> costoUnitario = costo * 1;
        }
        return costoUnitario;
    }

    private double calcularCosto() {
        log.info("Calcular costo");
        //comprobar que la cantidad sea diferente a cero
        if(CANTIDAD == 0){
            throw new ArithmeticException("Cantidad de la materia no puede ser igual a cero");
        }
        return PRECIO_COMPRA / CANTIDAD;
    }
}
