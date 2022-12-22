package com.mj.exception.business;

import com.mj.exception.handler.GlobalErrorCode;

public class VisitingIntervalException extends SimpleHotelException {

    public VisitingIntervalException(String message, GlobalErrorCode globalErrorCode) {
        super(message, globalErrorCode.getCode(), globalErrorCode.getHttpStatus());
    }
}
