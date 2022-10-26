package com.heybys.optimusamicus.order.exception;

import com.heybys.optimusamicus.order.error.OrderError;

public class OrderNotCreatedException extends OrderBaseException {

  private static final long serialVersionUID = 1L;

  public OrderNotCreatedException() {
    super(OrderError.ORDER_NOT_CREATED);
  }

  public OrderNotCreatedException(Exception e) {
    super(OrderError.ORDER_NOT_CREATED, e);
  }
}
