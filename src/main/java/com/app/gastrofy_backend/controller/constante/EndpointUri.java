package com.app.gastrofy_backend.controller.constante;

/**
 * Definir los  diferentes endpoints
 * a utilizar en la API
 */
public interface EndpointUri {
    interface Api {
        String BASE_PATH = "/api/v1";

        interface Usuarios {
            String USUARIOS = BASE_PATH + "/usuarios";
        }

        interface SistemaCostos {
            String SISTEMA_COSTOS = BASE_PATH + "/sistema-costos";
        }
    }
}
