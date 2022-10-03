package com.heybys.optimusamicus.order.model;

import org.springframework.stereotype.Component;

@Component
public class Barista {

  public Coffee makeCoffee(MenuItem menuItem) {
    return Coffee.builder().name(menuItem.getName()).price(menuItem.getPrice()).build();
  }
}
