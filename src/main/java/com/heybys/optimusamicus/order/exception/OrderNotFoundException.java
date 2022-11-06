package com.heybys.optimusamicus.order.exception;

public class OrderNotFoundException extends OrderBaseException {

  private static final long serialVersionUID = 1L;

  public OrderNotFoundException() {
    super(OrderError.ORDER_NOT_FOUND);
  }

  public OrderNotFoundException(Exception e) {
    super(OrderError.ORDER_NOT_FOUND, e);
  }
}
