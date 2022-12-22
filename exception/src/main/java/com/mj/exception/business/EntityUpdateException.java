package com.mj.exception.business;

import com.mj.exception.handler.GlobalErrorCode;

public class EntityUpdateException extends SimpleHotelException {

    public EntityUpdateException(String message, GlobalErrorCode globalErrorCode) {
        super(message, globalErrorCode.getCode(), globalErrorCode.getHttpStatus());
    }
}
