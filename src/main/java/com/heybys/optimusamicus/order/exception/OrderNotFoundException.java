package com.heybys.optimusamicus.order.exception;

import com.heybys.optimusamicus.order.error.OrderError;

public class OrderNotFoundException extends OrderBaseException {

  private static final long serialVersionUID = 1L;

  public OrderNotFoundException() {
    super(OrderError.ORDER_NOT_FOUND);
  }
}
