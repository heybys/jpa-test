package com.heybys.optimusamicus.common.advice;

import com.heybys.optimusamicus.common.error.CommonError;
import com.heybys.optimusamicus.common.exception.InvalidParameterException;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<CommonResponse> handleException(Exception exception) {
    log.error(
        "[Exception]: {}, [Cause]: {}, [Message]: {}",
        exception.getClass().getName(),
        exception.getCause(),
        exception.getMessage());

    CommonResponse response = new CommonResponse(StatusCode.FAIL, "Internal Server Error");

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidParameterException.class)
  protected ResponseEntity<CommonResponse> handleException(InvalidParameterException exception) {
    log.error(exception.getMessage());

    CommonResponse response = new CommonResponse(StatusCode.FAIL, exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<CommonResponse> handleException(
      MethodArgumentNotValidException exception) {

    BindingResult bindingResult = exception.getBindingResult();
    log.error("===============");
    for (FieldError error : bindingResult.getFieldErrors()) {
      log.error(
          "{} : {} (value:{})",
          error.getField(),
          error.getDefaultMessage(),
          error.getRejectedValue());
    }
    log.error("===============");
    log.error(exception.getMessage());

    CommonResponse response =
        new CommonResponse(StatusCode.FAIL, CommonError.METHOD_ARGUMENT_NOT_VALID.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<CommonResponse> handleException(
      MethodArgumentTypeMismatchException exception) {
    log.error(exception.getMessage());

    CommonResponse response =
        new CommonResponse(StatusCode.FAIL, CommonError.METHOD_ARGUMENT_TYPE_MISMATCH.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<CommonResponse> handleException(
      HttpRequestMethodNotSupportedException exception) {
    log.error(exception.getMessage());

    CommonResponse response =
        new CommonResponse(
            StatusCode.FAIL, CommonError.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getMessage());

    return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
  }
}
