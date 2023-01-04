package com.heybys.optimusamicus.shop.service.model;

import com.heybys.optimusamicus.shop.domain.Menu;
import com.heybys.optimusamicus.shop.domain.Shop;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class MenuBoard {

  private Long shopId;
  private String shopName;
  private boolean open;
  private List<MenuItem> menuItems;

  public static MenuBoard of(Shop shop, List<Menu> menus) {
    return new MenuBoard(shop, menus);
  }

  public MenuBoard(Shop shop, List<Menu> menus) {
    this.shopId = shop.getId();
    this.shopName = shop.getName();
    this.open = shop.isOpen();
    this.menuItems = convertWith(menus);
  }

  private List<MenuItem> convertWith(List<Menu> menus) {
    return menus.stream().map(MenuItem::of).collect(Collectors.toList());
  }

  @Data
  public static class MenuItem {

    private Long menuId;
    private String menuName;
    private String menuDescription;

    public static MenuItem of(Menu menu) {
      return new MenuItem(menu);
    }

    public MenuItem(Menu menu) {
      this.menuId = menu.getId();
      this.menuName = menu.getName();
      this.menuDescription = menu.getDescription();
    }
  }
}
