package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.exception.enums.AuthError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthBaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final AuthError error;

  public AuthBaseException(AuthError error) {
    this.error = error;
  }

  public AuthBaseException(AuthError error, Throwable e) {
    super(e);
    this.error = error;
  }

  public AuthError getError() {
    return error;
  }

  public void log() {
    log.error("[Exception]: " + this.getClass().getName() + ", [Cause]: " + this.getCause());
  }
}
