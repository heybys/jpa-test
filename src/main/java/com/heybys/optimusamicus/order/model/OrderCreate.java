package com.heybys.optimusamicus.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.order.entity.Order;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

public class OrderCreate {

  @Data
  public static class Request {

    @Builder
    public Request() {}

    public Order toOrder() {
      return Order.builder().build();
    }
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long orderId;
    private UUID orderSerialNumber;

    @Builder
    public Response(Long orderId, UUID orderSerialNumber) {
      this.orderId = orderId;
      this.orderSerialNumber = orderSerialNumber;
    }

    public static Response from(Order order) {
      return Response.builder()
          .orderId(order.getId())
          .orderSerialNumber(order.getSerialNumber())
          .build();
    }
  }
}
