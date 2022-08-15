package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.error.UserError;

public class UserNotFoundException extends UserBaseException {

  private static final long serialVersionUID = 1L;

  public UserNotFoundException() {
    super(UserError.USER_NOT_FOUND);
  }
}
