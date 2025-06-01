package com.ulsan.climbing.api.exception;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {
    String errorCode;

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public abstract int getStatusCode();

}
