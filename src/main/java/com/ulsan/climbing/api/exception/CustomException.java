package com.ulsan.climbing.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class CustomException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public CustomException(String message) {
        super(message);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
