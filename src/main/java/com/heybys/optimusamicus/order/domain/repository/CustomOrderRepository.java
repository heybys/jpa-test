package com.heybys.optimusamicus.order.domain.repository;

import com.heybys.optimusamicus.order.domain.entity.Order;

public interface CustomOrderRepository {

  Order insert(Order orderEntity);
}
