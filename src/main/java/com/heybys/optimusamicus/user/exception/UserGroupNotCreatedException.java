package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.error.UserError;

public class UserGroupNotCreatedException extends UserBaseException {

  private static final long serialVersionUID = 1L;

  public UserGroupNotCreatedException() {
    super(UserError.USER_GROUP_NOT_CREATED);
  }

  public UserGroupNotCreatedException(Exception e) {
    super(UserError.USER_GROUP_NOT_CREATED, e);
  }
}
