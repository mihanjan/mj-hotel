package com.mj.exception.business;

import org.springframework.http.HttpStatus;

public class ServiceRequestException extends SimpleHotelException {

    public ServiceRequestException(String message, String code, HttpStatus httpStatus) {
        super(message, code, httpStatus);
    }
}
