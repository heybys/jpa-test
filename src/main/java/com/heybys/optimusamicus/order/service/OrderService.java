package com.heybys.optimusamicus.order.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.utils.ApplicationEventPublisherProvider;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.event.OrderCreateEvent;
import com.heybys.optimusamicus.order.model.Coffee;
import com.heybys.optimusamicus.order.model.Customer;
import com.heybys.optimusamicus.order.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@LogExecutionTime
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  private final Customer customer;

  public void order(String menuName) {
    Coffee coffee = customer.order(menuName);

    log.info("Ordered coffee. {}", coffee);
  }

  @Transactional
  public Order retrieveOrder(Long orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow();
    order.refreshSerialNumber();
    return order;
  }

  @Transactional
  public Order createOrder(Order order) {
    Collection<Order> orders = new ArrayList<>();
    for (long l = 1L; l <= 5L; l++) {
      Order save = orderRepository.save(Order.builder().id(l).build());
      orders.add(save);
    }
    ApplicationEventPublisherProvider.getPublisher().publishEvent(OrderCreateEvent.of(orders));
    return order;
  }
}
