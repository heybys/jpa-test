package com.heybys.optimusamicus.order.exception;

import org.springframework.lang.Nullable;

public class OrderBaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Exception e;
  private final OrderError error;

  public OrderBaseException(OrderError error) {
    super(error.getMessage());
    this.e = null;
    this.error = error;
  }

  public OrderBaseException(OrderError error, Exception e) {
    super(e.getMessage(), e.getCause());
    this.e = e;
    this.error = error;
  }

  public @Nullable Exception getException() {
    return e;
  }

  public OrderError getError() {
    return error;
  }
}
