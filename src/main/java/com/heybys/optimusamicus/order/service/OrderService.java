package com.heybys.optimusamicus.order.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.event.OrderEvent;
import com.heybys.optimusamicus.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@LogExecutionTime
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  private final ApplicationEventPublisher publisher;

  @Transactional(readOnly = true)
  public Order retrieveOrder(Long orderId) {
    return orderRepository.findById(orderId).orElseThrow();
  }

  @Transactional
  public Order createOrder(Order order) {
    Order savedOrder = orderRepository.save(order);

    publisher.publishEvent(OrderEvent.of(savedOrder));
    publisher.publishEvent(OrderEvent.of(savedOrder));
    publisher.publishEvent(OrderEvent.of(savedOrder));

    return savedOrder;
  }
}
