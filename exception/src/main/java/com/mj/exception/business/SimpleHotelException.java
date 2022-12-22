package com.mj.exception.business;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class SimpleHotelException extends RuntimeException {

    private String code;
    private String message;
    private HttpStatus httpStatus;

    public SimpleHotelException(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
