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

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<HttpGlobalResponse<?>> handleDuplicateResourceException(DuplicateResourceException exception,
                                                                                        HttpServletRequest request){
        //almacenar error
        String errorMsg =  exception.getMessage();
        //almacenar error
        log.error("Solicitud invalida: {}", errorMsg);

        return ResponseEntity.badRequest()
                .body(HttpGlobalResponse.builder()
                        .timeStamp(now())
                        .statusCode(BAD_REQUEST.value())
                        .status(BAD_REQUEST)
                        .message("Solicitud invalida '%s'".formatted(request.getRequestURI()))
                        .data(of("error", errorMsg))
                        .build());
    }

    // Customized exception when a resource does not exist
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpGlobalResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        HttpServletRequest request){
        //almacenar error
        String errorMsg =  exception.getMessage();
        //almacenar error
        log.error("Solicitud invalida: {}", errorMsg);

        return ResponseEntity.badRequest()
                .body(HttpGlobalResponse.builder()
                        .timeStamp(now())
                        .statusCode(BAD_REQUEST.value())
                        .status(BAD_REQUEST)
                        .message("Solicitud invalida '%s'".formatted(request.getRequestURI()))
                        .data(of("error", errorMsg))
                        .build());
    }
}
