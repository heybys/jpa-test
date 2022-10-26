package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.error.UserError;

public class UserNotCreatedException extends UserBaseException {

  private static final long serialVersionUID = 1L;

  public UserNotCreatedException() {
    super(UserError.USER_NOT_CREATED);
  }

  public UserNotCreatedException(Exception e) {
    super(UserError.USER_NOT_CREATED, e);
  }
}
