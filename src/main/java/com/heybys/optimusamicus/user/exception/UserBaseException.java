package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.error.UserError;

public class UserBaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final UserError error;

  public UserBaseException(UserError error) {
    super(error.getMessage());
    this.error = error;
  }

  public UserBaseException(UserError error, Exception e) {
    super(e.getMessage(), e.getCause());
    this.error = error;
  }

  public UserError getError() {
    return error;
  }
}
