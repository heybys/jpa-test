package com.heybys.optimusamicus.user.exception;

public class UserNotPatchedException extends UserBaseException {

  private static final long serialVersionUID = 1L;

  public UserNotPatchedException() {
    super(UserError.USER_NOT_PATCHED);
  }

  public UserNotPatchedException(Exception e) {
    super(UserError.USER_NOT_PATCHED, e);
  }
}
