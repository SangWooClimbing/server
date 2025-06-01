package com.ulsan.climbing.api.exception;

public class AlreadyExistsEmailException extends CustomException {
  private static final String MESSAGE = "이미 가입된 이메일입니다.";

  public AlreadyExistsEmailException() {
    super(MESSAGE, "ALREADY_EXISTS_EMAIL");
  }

  @Override
  public int getStatusCode() {
    return 400;
  }
}
