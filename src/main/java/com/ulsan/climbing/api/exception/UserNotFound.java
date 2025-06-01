package com.ulsan.climbing.api.exception;

/**
 * status -> 404
 */
public class UserNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 않는 사용자입니다.";

    public UserNotFound() {
        super(MESSAGE, "USER_NOT_FOUND");
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
