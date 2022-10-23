package com.heybys.optimusamicus.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.order.entity.Order;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

public class OrderCreate {

  @Data
  public static class Request {

    @NotNull(message = "Need to orderId")
    private Long orderId;

    private String orderName;

    public Order toOrder() {
      return Order.builder().id(orderId).name(orderName).build();
    }
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long orderId;
    private UUID orderSerialNumber;
    private String orderName;

    @Builder
    public Response(Long orderId, UUID orderSerialNumber, String orderName) {
      this.orderId = orderId;
      this.orderSerialNumber = orderSerialNumber;
      this.orderName = orderName;
    }

    public static Response from(Order order) {
      return Response.builder()
          .orderId(order.getId())
          .orderSerialNumber(order.getSerialNumber())
          .orderName(order.getName())
          .build();
    }
  }
}
