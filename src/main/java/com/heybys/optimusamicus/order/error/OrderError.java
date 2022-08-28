package com.heybys.optimusamicus.order.error;

public enum OrderError {
  ORDER_NOT_FOUND("ORDER_NOT_FOUND", "User Not Found"),
  ORDER_NOT_CREATED("ORDER_NOT_CREATED", "User Not Created");

  private final String code;
  private final String message;

  OrderError(String code, String message) {
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
