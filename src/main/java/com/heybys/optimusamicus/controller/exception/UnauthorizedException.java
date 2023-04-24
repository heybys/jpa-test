package com.heybys.optimusamicus.controller.exception;

import com.heybys.optimusamicus.controller.exception.enums.AuthError;

public class UnauthorizedException extends AuthBaseException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException() {
    super(AuthError.UNAUTHORIZED);
  }

  public UnauthorizedException(Throwable cause) {
    super(AuthError.UNAUTHORIZED, cause);
  }
}
