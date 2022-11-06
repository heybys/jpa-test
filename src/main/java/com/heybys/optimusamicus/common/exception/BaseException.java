package com.heybys.optimusamicus.common.exception;

import org.springframework.lang.Nullable;

public class BaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Exception e;
  private final CommonError error;

  public BaseException(CommonError error) {
    super(error.getMessage());
    this.e = null;
    this.error = error;
  }

  public BaseException(CommonError error, Exception e) {
    super(e.getMessage(), e.getCause());
    this.e = e;
    this.error = error;
  }

  public @Nullable Exception getException() {
    return e;
  }

  public CommonError getError() {
    return error;
  }
}
