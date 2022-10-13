package com.heybys.optimusamicus.order.listener;

import com.heybys.optimusamicus.common.utils.ApplicationEventPublisherProvider;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.event.OrderCreateEvent;
import com.heybys.optimusamicus.order.event.OrderUpdateEvent;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderListener {

  @PostPersist
  public void onPostPersist(Order order) {
    ApplicationEventPublisherProvider.getPublisher().publishEvent(OrderCreateEvent.of(order));
  }

  @PostUpdate
  public void onPostUpdate(Order order) {
    ApplicationEventPublisherProvider.getPublisher().publishEvent(OrderUpdateEvent.of(order));
  }
}
