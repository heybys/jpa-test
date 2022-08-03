package com.heybys.optimusamicus.common.error;

public enum CommonError {

    INVALID_PARAMETER("INVALID_PARAMETER", "Invalid Request Parameter"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User Not Found");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CommonError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
