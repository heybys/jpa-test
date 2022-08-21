package com.heybys.optimusamicus.user.error;

public enum UserError {
  USER_NOT_FOUND("USER_NOT_FOUND", "User Not Found"),
  USER_NOT_CREATED("USER_NOT_CREATED", "User Not Created"),
  USER_GROUP_NOT_FOUND("USER_GROUP_NOT_FOUND", "User Group Not Found");

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
