package com.heybys.optimusamicus.order.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@LogExecutionTime
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  @Transactional(readOnly = true)
  public Order retrieveOrder(Long orderId) {
    return orderRepository.findById(orderId).orElseThrow();
  }

  @Transactional
  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }
}
