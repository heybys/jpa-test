package com.heybys.optimusamicus.order.event;

import com.heybys.optimusamicus.order.entity.Order;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Getter
@ToString
@Value(staticConstructor = "of")
public class OrderCreateEvent {

  Collection<Order> orders;

  public static OrderCreateEvent of(Order order) {
    Collection<Order> orders = new ArrayList<>();
    orders.add(order);
    return new OrderCreateEvent(orders);
  }
  ;
}
