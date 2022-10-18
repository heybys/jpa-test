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

    public Order toOrder() {
      return Order.builder().id(orderId).build();
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
