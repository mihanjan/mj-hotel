package com.mj.exception.business;

import com.mj.exception.handler.GlobalErrorCode;

public class EntityNotFoundException extends SimpleHotelException {

    public EntityNotFoundException(String message, GlobalErrorCode globalErrorCode) {
        super(message, globalErrorCode.getCode(), globalErrorCode.getHttpStatus());
    }
}
