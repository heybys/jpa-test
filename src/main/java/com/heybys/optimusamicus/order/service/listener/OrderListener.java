package com.heybys.optimusamicus.order.service.listener;

import com.heybys.optimusamicus.common.utils.ApplicationEventPublisherProvider;
import com.heybys.optimusamicus.order.domain.OrderEvent;
import com.heybys.optimusamicus.order.domain.OrderEvent.Type;
import com.heybys.optimusamicus.order.domain.entity.Order;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderListener {

  @PrePersist
  public void onPrePersist(Order order) {
    ApplicationEventPublisherProvider.publishEvent(OrderEvent.of(order, Type.CREATE));
  }

  @PreUpdate
  public void onPreUpdate(Order order) {
    ApplicationEventPublisherProvider.publishEvent(OrderEvent.of(order, Type.UPDATE));
  }
}
