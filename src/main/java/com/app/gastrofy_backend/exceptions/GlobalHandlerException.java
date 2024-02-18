package com.app.gastrofy_backend.exceptions;

import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<HttpGlobalResponse<ErrorResponse>> handleDuplicateResourceException(DuplicateResourceException exception,
                                                                                              HttpServletRequest request){
        //almacenar error
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensaje(exception.getMessage())
                .build();
        log.error("Solicitud invalida: {}", exception.getMessage());

        return ResponseEntity.badRequest()
                .body(HttpGlobalResponse.<ErrorResponse>builder()
                        .timeStamp(now())
                        .statusCode(BAD_REQUEST.value())
                        .status(BAD_REQUEST)
                        .message("Solicitud invalida '%s' ".formatted(request.getRequestURI()))
                        .data(of("error", errorResponse))
                        .build());
    }

    // Customized exception when a resource does not exist
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpGlobalResponse<ErrorResponse>> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        HttpServletRequest request){
        //almacenar error
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensaje(exception.getMessage())
                .build();
        log.error("Solicitud invalida: {}", exception.getMessage());

        return ResponseEntity.status(NOT_FOUND)
                .body(HttpGlobalResponse.<ErrorResponse>builder()
                        .timeStamp(now())
                        .statusCode(NOT_FOUND.value())
                        .status(NOT_FOUND)
                        .message("Solicitud invalida '%s' ".formatted(request.getRequestURI()))
                        .data(of("error", errorResponse))
                        .build());
    }
}
