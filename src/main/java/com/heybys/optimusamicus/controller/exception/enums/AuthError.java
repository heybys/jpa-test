package com.heybys.optimusamicus.controller.exception.enums;

public enum AuthError {
  UNAUTHORIZED("UNAUTHORIZED", "Unauthorized");

  private final String code;
  private final String message;

  AuthError(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
