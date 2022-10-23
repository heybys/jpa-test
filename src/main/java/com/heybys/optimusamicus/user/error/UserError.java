package com.heybys.optimusamicus.user.error;

public enum UserError {
  USER_NOT_FOUND("USER_NOT_FOUND", "User Not Found"),
  USER_NOT_CREATED("USER_NOT_CREATED", "User Not Created"),
  USER_NOT_PATCHED("USER_NOT_PATCHED", "User Not Patched"),
  USER_GROUP_NOT_FOUND("USER_GROUP_NOT_FOUND", "User Group Not Found"),
  USER_GROUP_NOT_CREATED("USER_GROUP_NOT_CREATED", "User Group Not Created"),
  AUTHENTICATION_FAILED("AUTHENTICATION_FAILED", "Authentication Failed");

  private final String code;
  private final String message;

  UserError(String code, String message) {
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
