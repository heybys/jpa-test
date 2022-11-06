package com.heybys.optimusamicus.common.exception;

public enum CommonError {
  INVALID_PARAMETER("INVALID_PARAMETER", "Invalid Parameter"),
  METHOD_ARGUMENT_NOT_VALID("METHOD_ARGUMENT_NOT_VALID", "Method Argument Not Valid"),
  METHOD_ARGUMENT_TYPE_MISMATCH("METHOD_ARGUMENT_TYPE_MISMATCH", "Method Argument Type Mismatch"),
  HTTP_REQUEST_METHOD_NOT_SUPPORTED(
      "HTTP_REQUEST_METHOD_NOT_SUPPORTED", "Http Request Method Not Supported");

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
