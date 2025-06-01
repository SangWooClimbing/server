package com.ulsan.climbing.api.exception;

public class UnValidTokenException extends CustomException {
    private static final String MESSAGE = "잘못된 토큰입니다.";
    public UnValidTokenException() {
        super(MESSAGE, "UNVALID_TOKEN");
    }
    @Override
    public int getStatusCode() {
        return 403;
    }
}
