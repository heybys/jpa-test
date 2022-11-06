package com.heybys.optimusamicus.common.exception;

public class InvalidParameterException extends BaseException {

  private static final long serialVersionUID = 1L;

  public InvalidParameterException() {
    super(CommonError.INVALID_PARAMETER);
  }

  public InvalidParameterException(Exception e) {
    super(CommonError.INVALID_PARAMETER, e);
  }
}
