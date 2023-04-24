package com.heybys.optimusamicus.controller.exception.handler;

import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.controller.AuthController;
import com.heybys.optimusamicus.controller.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = {AuthController.class})
public class AuthExceptionHandler {

  @ExceptionHandler(UnauthorizedException.class)
  protected ResponseEntity<CommonResponse> handleException(UnauthorizedException exception) {
    exception.log();

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail());
  }
}
