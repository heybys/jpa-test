package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.error.UserError;
import org.springframework.lang.Nullable;

public class UserBaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Exception e;
  private final UserError error;

  public UserBaseException(UserError error) {
    super(error.getMessage());
    this.e = null;
    this.error = error;
  }

  public UserBaseException(UserError error, Exception e) {
    super(e.getMessage(), e.getCause());
    this.e = e;
    this.error = error;
  }

  public @Nullable Exception getException() {
    return e;
  }

  public UserError getError() {
    return error;
  }
}
