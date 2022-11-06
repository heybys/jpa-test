package com.heybys.optimusamicus.order.domain;

import com.heybys.optimusamicus.order.domain.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventHandler {

  @Transactional
  @EventListener(condition = "#event.type.name() == 'CREATE'")
  public void handleCreateEvent(OrderEvent event) {
    log.debug("handle create event. {}", event);
  }

  @Transactional
  @EventListener(condition = "#event.type.name() == 'UPDATE'")
  public void handleUpdateEvent(OrderEvent event) {
    log.debug("handle update event. {}", event);
  }
}
