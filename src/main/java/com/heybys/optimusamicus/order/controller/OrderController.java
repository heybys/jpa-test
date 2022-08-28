package com.heybys.optimusamicus.order.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.order.dto.search.OrderSearch;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.exception.OrderNotFoundException;
import com.heybys.optimusamicus.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/{orderId}")
  public ResponseEntity<CommonResponse> retrieveOrder(@PathVariable Long orderId) {

    try {
      // call service
      Order retrievedOrder = orderService.retrieveOrder(orderId);

      // convert entity to dto
      OrderSearch.Response response = OrderSearch.Response.from(retrievedOrder);

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.OK);
    } catch (Exception e) {
      throw new OrderNotFoundException();
    }
  }
}
