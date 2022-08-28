package com.heybys.optimusamicus.order.dto.create;

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

    private Long id;
    private UUID serialNumber;

    @Builder
    public Response(Long id, UUID serialNumber) {
      this.id = id;
      this.serialNumber = serialNumber;
    }

    public static Response from(Order order) {
      return Response.builder().id(order.getId()).serialNumber(order.getSerialNumber()).build();
    }
  }
}
