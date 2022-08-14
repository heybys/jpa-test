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
    SUCCESS, FAIL
  }

  private StatusCode statusCode;
  private String message;
  private Object data;

  public CommonResponse(StatusCode statusCode) {
    this.statusCode = statusCode;
  }

  public CommonResponse(StatusCode statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public CommonResponse(StatusCode statusCode, Object data) {
    this.statusCode = statusCode;
    this.data = data;
  }
}
