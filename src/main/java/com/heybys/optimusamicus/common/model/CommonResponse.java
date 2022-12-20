package com.heybys.optimusamicus.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CommonResponse {

  public enum StatusCode {
    SUCCESS,
    FAIL
  }

  private StatusCode statusCode;
  private String message;
  private Object payload;

  public CommonResponse(StatusCode statusCode) {
    this.statusCode = statusCode;
  }

  public CommonResponse message(String message) {
    this.message = message;
    return this;
  }

  public CommonResponse payload(Object payload) {
    this.payload = payload;
    return this;
  }

  public static CommonResponse success() {
    return new CommonResponse(StatusCode.SUCCESS);
  }

  public static CommonResponse success(String message) {
    return success().message(message);
  }

  public static CommonResponse success(Object payload) {
    return success().payload(payload);
  }

  public static CommonResponse fail() {
    return new CommonResponse(StatusCode.FAIL);
  }

  public static CommonResponse fail(String message) {
    return fail().message(message);
  }

  public static CommonResponse fail(Object payload) {
    return fail().payload(payload);
  }
}
