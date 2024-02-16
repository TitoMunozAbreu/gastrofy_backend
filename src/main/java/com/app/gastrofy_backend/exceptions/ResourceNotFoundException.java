package com.app.gastrofy_backend.exceptions;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
