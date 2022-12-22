package com.mj.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalHotelExceptionHandler extends ResponseEntityExceptionHandler {

    private final ServiceType serviceType;

    @ExceptionHandler(SimpleHotelException.class)
    protected ResponseEntity handleHotelException(SimpleHotelException simpleHotelException, Locale locale) {
        return ResponseEntity
                .status(simpleHotelException.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(simpleHotelException.getCode())
                        .message(simpleHotelException.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception e, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code(GlobalErrorCode.getUnknownError(serviceType).getCode())
                        .message(e.toString())
                        .build());
    }
}
