package com.heybys.optimusamicus.order.domain;

import com.heybys.optimusamicus.order.domain.entity.Order;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Getter
@ToString
@Value(staticConstructor = "of")
public class OrderCreateEvent {

  Order order;
}
