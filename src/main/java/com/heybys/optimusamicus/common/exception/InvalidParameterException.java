package com.heybys.optimusamicus.common.exception;

import com.heybys.optimusamicus.common.error.CommonError;

public class InvalidParameterException extends BaseException {

  private static final long serialVersionUID = 1L;

  public InvalidParameterException() {
    super(CommonError.INVALID_PARAMETER);
  }
}
