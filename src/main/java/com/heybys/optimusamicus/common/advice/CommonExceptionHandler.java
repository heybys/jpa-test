package com.heybys.optimusamicus.common.advice;

import com.heybys.optimusamicus.common.exception.InvalidParameterException;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order()
public class CommonExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<CommonResponse> handleException(Exception exception) {
    logger.error("[Exception]: {}, [Cause]: {}, [Message]: {}", exception.getClass().getName(),
        exception.getCause(), exception.getMessage());
    CommonResponse response = new CommonResponse(StatusCode.FAIL, "Internal Server Error");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidParameterException.class)
  protected ResponseEntity<CommonResponse> handleException(InvalidParameterException exception) {
    logger.error(exception.getMessage());
    CommonResponse response = new CommonResponse(StatusCode.FAIL, exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
