package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.exception.enums.AuthError;

public class UnauthorizedException extends AuthBaseException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException() {
    super(AuthError.UNAUTHORIZED);
  }

  public UnauthorizedException(Throwable cause) {
    super(AuthError.UNAUTHORIZED, cause);
  }
}
