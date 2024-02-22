package com.app.gastrofy_backend.model.request;

public record MateriaPrimaRequest (String codigoProducto,
                                   String nombre,
                                   double precioCompra,
                                   double cantidad,
                                   String presentacion,
                                   String proveedor,
                                   String categoria) {}
