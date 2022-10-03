package com.heybys.optimusamicus.order.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Menu {

  private final List<MenuItem> items;

  public Menu() {
    this.items = new ArrayList<>();
    items.add(MenuItem.builder().name("mocha cappuccino").price(1800).build());
    items.add(MenuItem.builder().name("caramel macchiato").price(2100).build());
    items.add(MenuItem.builder().name("americano").price(1200).build());
    items.add(MenuItem.builder().name("cold brew").price(1500).build());
  }

  public MenuItem choose(String menuName) {
    return items.stream().filter(item -> menuName.equals(item.getName())).findFirst().orElseThrow();
  }
}
