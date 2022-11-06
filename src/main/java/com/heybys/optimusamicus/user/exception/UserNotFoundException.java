package com.heybys.optimusamicus.user.exception;

public class UserNotFoundException extends UserBaseException {

  private static final long serialVersionUID = 1L;

  public UserNotFoundException() {
    super(UserError.USER_NOT_FOUND);
  }

  public UserNotFoundException(Exception e) {
    super(UserError.USER_NOT_FOUND, e);
  }
}
