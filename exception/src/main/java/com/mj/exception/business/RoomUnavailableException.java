package com.mj.exception.business;

import com.mj.exception.handler.GlobalErrorCode;

public class RoomUnavailableException extends SimpleHotelException {

    public RoomUnavailableException(String message, GlobalErrorCode globalErrorCode) {
        super(message, globalErrorCode.getCode(), globalErrorCode.getHttpStatus());
    }
}
