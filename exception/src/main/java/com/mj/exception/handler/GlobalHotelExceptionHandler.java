package com.mj.exception.handler;

import com.mj.exception.business.SimpleHotelException;
import com.mj.exception.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalHotelExceptionHandler extends ResponseEntityExceptionHandler {

    private final ServiceType serviceType;

    @ExceptionHandler(SimpleHotelException.class)
    protected ResponseEntity handleHotelException(SimpleHotelException ex, Locale locale) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception ex, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .code(GlobalErrorCode.getUnknownError(serviceType).getCode())
                        .message(getRootCauseMessage(ex))
                        .build());
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .code(GlobalErrorCode.getValidationError(serviceType).getCode())
                        .message(getRootCauseMessage(ex))
                        .build());
    }
}
