package com.heybys.optimusamicus.order.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Coffee {

  private final String name;

  private final Integer price;

  @Builder
  public Coffee(String name, Integer price) {
    this.name = name;
    this.price = price;
  }
}
