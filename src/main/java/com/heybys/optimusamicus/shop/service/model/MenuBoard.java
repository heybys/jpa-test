package com.heybys.optimusamicus.shop.service.model;

import com.heybys.optimusamicus.shop.domain.Menu;
import com.heybys.optimusamicus.shop.domain.OptionGroup;
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
    private Long menuPrice;
    private List<OptionGroupItem> menuOptionGroupItems;

    public static MenuItem of(Menu menu) {
      return new MenuItem(menu);
    }

    public MenuItem(Menu menu) {
      this.menuId = menu.getId();
      this.menuName = menu.getName();
      this.menuDescription = menu.getDescription();
      this.menuPrice = menu.getPrice();
      this.menuOptionGroupItems = convertWith(menu.getOptionGroups());
    }

    private List<OptionGroupItem> convertWith(List<OptionGroup> optionGroups) {
      return optionGroups.stream().map(OptionGroupItem::of).collect(Collectors.toList());
    }
  }

  @Data
  public static class OptionGroupItem {

    private Long optionGroupId;
    private String optionGroupName;

    public static OptionGroupItem of(OptionGroup optionGroup) {
      return new OptionGroupItem(optionGroup);
    }

    public OptionGroupItem(OptionGroup optionGroup) {
      this.optionGroupId = optionGroup.getId();
      this.optionGroupName = optionGroup.getName();
    }
  }
}
