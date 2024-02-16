package com.app.gastrofy_backend.exceptions;

public class DuplicateResourceException extends Throwable {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
