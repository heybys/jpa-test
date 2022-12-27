package com.heybys.optimusamicus.common.exception;

public class InvalidParameterException extends BaseException {

  private static final long serialVersionUID = 1L;

  public InvalidParameterException() {
    super(CommonError.INVALID_PARAMETER);
  }

  public InvalidParameterException(String message) {
    super(CommonError.INVALID_PARAMETER, message);
  }

  public InvalidParameterException(Throwable cause) {
    super(CommonError.INVALID_PARAMETER, cause);
  }

  public InvalidParameterException(String message, Throwable cause) {
    super(CommonError.INVALID_PARAMETER, message, cause);
  }
}
