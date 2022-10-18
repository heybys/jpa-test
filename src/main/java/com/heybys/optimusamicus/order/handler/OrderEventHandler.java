package com.heybys.optimusamicus.order.handler;

import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.entity.OrderCopy;
import com.heybys.optimusamicus.order.event.OrderCreateEvent;
import com.heybys.optimusamicus.order.event.OrderUpdateEvent;
import com.heybys.optimusamicus.order.repository.OrderCopyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventHandler {

  // private final OrderCopyService orderCopyService;
  private final OrderCopyRepository orderCopyRepository;

  @Transactional
  @EventListener
  public void listen(OrderCreateEvent event) {
    Order order = event.getOrder();
    log.info("[Event Handler] created order = " + order);

    orderCopyRepository.save(OrderCopy.builder().order(order).build());
  }

  @Transactional
  @EventListener
  public void listen(OrderUpdateEvent event) {
    Order order = event.getOrder();
    log.info("[Event Handler] updated order = " + order);

    OrderCopy orderCopy = orderCopyRepository.findById(order.getId()).orElseThrow();
    orderCopy.updateBy(order);
  }
}
