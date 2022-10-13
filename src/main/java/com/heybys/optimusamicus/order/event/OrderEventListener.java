package com.heybys.optimusamicus.order.event;

import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.entity.OrderCopy;
import com.heybys.optimusamicus.order.repository.OrderCopyRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

  // private final OrderCopyService orderCopyService;
  private final OrderCopyRepository orderCopyRepository;

  @Transactional
  @EventListener(OrderCreateEvent.class)
  public void listen(OrderCreateEvent event) {
    Collection<Order> orders = event.getOrders();
    log.info("created orders = " + orders);
    // orderCopyService.createOrderCopyBy(order);

    for (Order order : orders) {
      orderCopyRepository.save(OrderCopy.builder().order(order).build());
    }
  }

  @Transactional
  @EventListener(OrderUpdateEvent.class)
  public void listen(OrderUpdateEvent event) {
    Order order = event.getOrder();
    log.info("updated order = " + order);
    // orderCopyService.updateOrderCopyBy(order);
    OrderCopy orderCopy = orderCopyRepository.findById(order.getId()).orElseThrow();
    orderCopy.updateBy(order);
  }
}
