package com.heybys.optimusamicus.common.exception;

import com.heybys.optimusamicus.common.error.CommonError;

public class BaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final CommonError error;

  public BaseException(CommonError error) {
    super(error.getMessage());
    this.error = error;
  }

  public BaseException(CommonError error, Exception e) {
    super(e.getMessage(), e.getCause());
    this.error = error;
  }

  public CommonError getError() {
    return error;
  }
}
