package com.heybys.jpatest.common.exception;

import com.heybys.jpatest.common.error.CommonError;

public class UserNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super(CommonError.USER_NOT_FOUND);
    }
}
