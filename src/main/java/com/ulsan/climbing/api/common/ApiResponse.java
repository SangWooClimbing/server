package com.ulsan.climbing.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ulsan.climbing.api.exception.CustomException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApiResponse<T> {
    private final int httpStatus;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    @Builder
    public ApiResponse(int httpStatus, String code, String message, T data){
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), null, HttpStatus.OK.name(), data);
    }

    public static ApiResponse<?> error(CustomException errorCode) {
        return ApiResponse.builder()
                .httpStatus(errorCode.getStatusCode())
                .code(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
    }

    public static ApiResponse<?> error(CustomException errorCode, String message) {
        return ApiResponse.builder()
                .httpStatus(errorCode.getStatusCode())
                .code(errorCode.getErrorCode())
                .message(message)
                .build();
    }
}
