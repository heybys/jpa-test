package com.heybys.optimusamicus.order.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Customer {

  private final Menu menu;

  private final Barista barista;

  public Coffee order(String menuName) {
    MenuItem menuItem = menu.choose(menuName);
    return barista.makeCoffee(menuItem);
  }
}
