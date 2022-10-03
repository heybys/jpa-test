package com.heybys.optimusamicus.order.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuItem {

  private final String name;

  private final Integer price;

  @Builder
  public MenuItem(String name, Integer price) {
    this.name = name;
    this.price = price;
  }
}
