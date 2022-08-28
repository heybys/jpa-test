package com.heybys.optimusamicus.order.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@LogExecutionTime
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public Order retrieveOrder(Long orderId) {
    return null;
  }
}
