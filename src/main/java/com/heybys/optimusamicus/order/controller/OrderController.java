package com.heybys.optimusamicus.order.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.exception.OrderNotCreatedException;
import com.heybys.optimusamicus.order.exception.OrderNotFoundException;
import com.heybys.optimusamicus.order.model.OrderCreate;
import com.heybys.optimusamicus.order.model.OrderCreate.Response;
import com.heybys.optimusamicus.order.model.OrderSearch;
import com.heybys.optimusamicus.order.service.OrderService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/coffee")
  public ResponseEntity<CommonResponse> orderCoffee(@RequestParam String menuName) {
    try {
      this.orderService.order(menuName);

      return ResponseEntity.ok(CommonResponse.success());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("")
  public ResponseEntity<CommonResponse> retrieveOrder(@RequestParam String name) {

    try {
      List<Order> orders = orderService.retrieveOrders(name);

      List<OrderSearch.Response> response =
          orders.stream().map(OrderSearch.Response::from).collect(Collectors.toList());

      return ResponseEntity.ok(CommonResponse.success(response));
    } catch (Exception e) {
      throw new OrderNotFoundException(e);
    }
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<CommonResponse> retrieveOrder(@PathVariable Long orderId) {

    try {
      Order retrievedOrder = orderService.retrieveOrder(orderId);

      OrderSearch.Response response = OrderSearch.Response.from(retrievedOrder);

      return ResponseEntity.ok(CommonResponse.success(response));
    } catch (Exception e) {
      throw new OrderNotFoundException(e);
    }
  }

  @PostMapping("")
  public ResponseEntity<CommonResponse> createOrder(
      @RequestBody @Valid OrderCreate.Request request) {

    try {
      Order order = request.toOrder();

      Order createdOrder = orderService.createOrder(order);

      Response response = Response.from(createdOrder);
      URI uri = URI.create("api/v1/orders/" + response.getOrderId());

      return ResponseEntity.created(uri).body(CommonResponse.success(response));
    } catch (Exception e) {
      throw new OrderNotCreatedException(e);
    }
  }
}
