package com.heybys.optimusamicus.order.repository;

import com.heybys.optimusamicus.order.entity.Order;

public interface CustomOrderRepository {
  Order insert(Order orderEntity);
}
