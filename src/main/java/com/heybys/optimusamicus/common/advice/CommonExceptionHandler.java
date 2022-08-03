package com.heybys.optimusamicus.common.advice;

import com.heybys.optimusamicus.common.exception.UserNotFoundException;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<CommonResponse> handleException(UserNotFoundException ex) {
        logger.error(ex.getMessage());
        CommonResponse response = new CommonResponse(StatusCode.FAIL, ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
