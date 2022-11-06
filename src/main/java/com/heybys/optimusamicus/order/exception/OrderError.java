package com.heybys.optimusamicus.order.exception;

public enum OrderError {
  ORDER_NOT_FOUND("ORDER_NOT_FOUND", "Order Not Found"),
  ORDER_NOT_CREATED("ORDER_NOT_CREATED", "Order Not Created");

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
