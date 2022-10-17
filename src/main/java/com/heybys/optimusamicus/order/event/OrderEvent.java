package com.heybys.optimusamicus.order.event;

import com.heybys.optimusamicus.order.entity.Order;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Getter
@ToString
@Value(staticConstructor = "of")
public class OrderEvent {

  Order order;

  Type type;

  public enum Type {
    CREATE,
    UPDATE
  }
}
