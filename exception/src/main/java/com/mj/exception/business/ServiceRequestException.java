package com.mj.exception.business;

import org.springframework.http.HttpStatus;

public class ServiceResponseException extends SimpleHotelException {

    public ServiceResponseException(String message, String code, HttpStatus httpStatus) {
        super(message, code, httpStatus);
    }
}
