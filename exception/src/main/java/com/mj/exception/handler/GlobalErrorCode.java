package com.mj.exception.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode {
    GUEST_UNKNOWN_ERROR("GUEST_1000", BAD_REQUEST),
    GUEST_NOT_FOUND("GUEST_1001", NOT_FOUND),
    GUEST_VALIDATION_ERROR("GUEST_1002", BAD_REQUEST),
//    GUEST_ALREADY_EXISTS("GUEST_1003", BAD_REQUEST),

    ROOM_UNKNOWN_ERROR("ROOM_1000", BAD_REQUEST),
    ROOM_NOT_FOUND("ROOM_1001", NOT_FOUND),
    ROOM_VALIDATION_ERROR("ROOM_1002", BAD_REQUEST),
//    ROOM_UPDATE_OCCUPATION("ROOM_1003", CONFLICT),

    VISIT_UNKNOWN_ERROR("VISIT_1000", BAD_REQUEST),
    VISIT_ROOM_OCCUPIED("VISIT_1001", BAD_REQUEST),
    VISIT_INTERVAL("VISIT_1002", BAD_REQUEST),
    VISIT_NOT_FOUND("VISIT_1003", NOT_FOUND),
    VISIT_VALIDATION_ERROR("VISIT_1004", BAD_REQUEST);

    private final String code;
    private final HttpStatus httpStatus;

    static GlobalErrorCode getUnknownError(ServiceType serviceType) {
        return (switch (serviceType) {
            case GUEST -> GUEST_UNKNOWN_ERROR;
            case ROOM -> ROOM_UNKNOWN_ERROR;
            case VISIT -> VISIT_UNKNOWN_ERROR;
        });
    }

    static GlobalErrorCode getValidationError(ServiceType serviceType) {
        return (switch (serviceType) {
            case GUEST -> GUEST_VALIDATION_ERROR;
            case ROOM -> ROOM_VALIDATION_ERROR;
            case VISIT -> VISIT_VALIDATION_ERROR;
        });
    }
}
