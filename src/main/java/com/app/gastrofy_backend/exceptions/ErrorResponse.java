package com.app.gastrofy_backend.exceptions;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String mensaje;
}
