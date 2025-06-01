package com.ulsan.climbing.api.exception;

public class UnAuthorizedException extends CustomException {
    public UnAuthorizedException() {
        super("권한이 없습니다.", "Unauthorized");
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
