package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.error.UserError;

public class AuthenticationFailedException extends UserBaseException {

  private static final long serialVersionUID = 1L;

  public AuthenticationFailedException() {
    super(UserError.AUTHENTICATION_FAILED);
  }
}
