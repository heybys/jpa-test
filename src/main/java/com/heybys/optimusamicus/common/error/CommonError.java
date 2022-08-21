package com.heybys.optimusamicus.common.error;

public enum CommonError {
  INVALID_PARAMETER("INVALID_PARAMETER", "Invalid Request Parameter"),
  METHOD_ARGUMENT_NOT_VALID("METHOD_ARGUMENT_NOT_VALID", "Method Argument Not Valid");

  private final String code;
  private final String message;

  CommonError(String code, String message) {
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
