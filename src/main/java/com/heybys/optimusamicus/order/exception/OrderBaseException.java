package com.heybys.optimusamicus.order.exception;

import com.heybys.optimusamicus.order.error.OrderError;

public class OrderBaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final OrderError error;

  public OrderBaseException(OrderError error) {
    super(error.getMessage());
    this.error = error;
  }

  public OrderBaseException(OrderError error, Exception e) {
    super(e.getMessage(), e.getCause());
    this.error = error;
  }

  public OrderError getError() {
    return error;
  }
}
