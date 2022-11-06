package com.heybys.optimusamicus.user.exception;

public class UserGroupNotFoundException extends UserBaseException {

  private static final long serialVersionUID = 1L;

  public UserGroupNotFoundException() {
    super(UserError.USER_GROUP_NOT_FOUND);
  }

  public UserGroupNotFoundException(Exception e) {
    super(UserError.USER_GROUP_NOT_FOUND, e);
  }
}
