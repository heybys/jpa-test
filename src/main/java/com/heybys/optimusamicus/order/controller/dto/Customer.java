package com.heybys.optimusamicus.order.controller.dto;

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
