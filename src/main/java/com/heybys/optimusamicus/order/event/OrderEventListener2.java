package com.heybys.optimusamicus.order.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventListener2 {

  @EventListener
  public void listen(OrderEvent event) {
    log.info("event = " + event);
  }
}
