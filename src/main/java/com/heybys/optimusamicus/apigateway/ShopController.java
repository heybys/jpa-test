package com.heybys.optimusamicus.apigateway;

import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.shop.service.ShopService;
import com.heybys.optimusamicus.shop.service.model.MenuBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/shop")
public class ShopController {

  public final ShopService shopService;

  @GetMapping("/menu-board")
  public ResponseEntity<CommonResponse> getMenuBoard(Long shopId) {

    try {
      MenuBoard menuBoard = shopService.getMenuBoard(shopId);

      return ResponseEntity.ok(CommonResponse.success(menuBoard));
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }
}
