package com.heybys.jpatest.common.exception;

import com.heybys.jpatest.common.error.CommonError;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final CommonError error;

    public BaseException(CommonError error) {
        super(error.getMessage());
        this.error = error;
    }

    public CommonError getError() {
        return error;
    }
}
