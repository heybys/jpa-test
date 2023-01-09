package com.heybys.optimusamicus.shop.service;

import com.heybys.optimusamicus.shop.domain.Menu;
import com.heybys.optimusamicus.shop.domain.MenuRepository;
import com.heybys.optimusamicus.shop.domain.Shop;
import com.heybys.optimusamicus.shop.domain.ShopRepository;
import com.heybys.optimusamicus.shop.service.model.MenuBoard;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShopService {

  private final MenuRepository menuRepository;
  private final ShopRepository shopRepository;

  @Transactional(readOnly = true)
  public MenuBoard getMenuBoard(Long shopId) {
    Shop shop = shopRepository.findById(shopId).orElseThrow();
    List<Menu> menus = menuRepository.findByShopId(shopId);

    return MenuBoard.of(shop, menus);
  }

  public void updateMenuBoard(MenuBoard menuBoard) {
    // in develop
  }
}
