package com.heybys.optimusamicus.common.exception;

import com.heybys.optimusamicus.common.error.CommonError;

public class UserNotFoundException extends BaseException {

  private static final long serialVersionUID = 1L;

  public UserNotFoundException() {
    super(CommonError.USER_NOT_FOUND);
  }
}
