package com.heybys.optimusamicus.order.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.order.domain.entity.Order;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

public class OrderSearch {

  @Data
  public static class Request {

    private Long orderId;
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
