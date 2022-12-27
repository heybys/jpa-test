package com.heybys.optimusamicus.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final CommonError error;

  public BaseException(CommonError error) {
    this.error = error;
  }

  public BaseException(CommonError error, String message) {
    super(message);
    this.error = error;
  }

  public BaseException(CommonError error, Throwable cause) {
    super(cause);
    this.error = error;
  }

  public BaseException(CommonError error, String message, Throwable cause) {
    super(message, cause);
    this.error = error;
  }

  public CommonError getError() {
    return error;
  }

  public void log() {
    log.error("[Exception]: " + this.getClass().getName() + ", [Cause]: " + this.getCause());
  }
}
