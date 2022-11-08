package com.heybys.optimusamicus.order.exception;

import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.order.controller.OrderController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = {OrderController.class})
public class OrderExceptionHandler {

  @ExceptionHandler(OrderNotFoundException.class)
  protected ResponseEntity<CommonResponse> handleException(OrderNotFoundException exception) {
    String message = exception.getMessage();

    log.error(message);
    return ResponseEntity.notFound().build();
  }
}
