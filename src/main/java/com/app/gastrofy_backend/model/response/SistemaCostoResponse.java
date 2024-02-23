package com.app.gastrofy_backend.model.response;

import com.app.gastrofy_backend.model.enums.GlobalPresentacion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class SistemaCostoResponse {
    private Integer idSistemaCosto;
    private String nombreEmpresa;
    private GlobalPresentacion globalPresentacion;
}
