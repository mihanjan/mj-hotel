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

    ROOM_UNKNOWN_ERROR("ROOM_1000", BAD_REQUEST),
    ROOM_NOT_FOUND("ROOM_1001", NOT_FOUND),
    ROOM_UPDATE_OCCUPATION("ROOM_1002", CONFLICT),

    VISIT_UNKNOWN_ERROR("VISIT_1000", BAD_REQUEST),
    VISIT_ROOM_OCCUPIED("VISIT_1001", BAD_REQUEST),
    VISIT_INTERVAL("VISIT_1002", BAD_REQUEST),
    VISIT_NOT_FOUND("VISIT_1003", NOT_FOUND);

    private final String code;
    private final HttpStatus httpStatus;

    static GlobalErrorCode getUnknownError(ServiceType serviceType) {
        return (switch (serviceType) {
            case GUEST -> GUEST_UNKNOWN_ERROR;
            case ROOM -> ROOM_UNKNOWN_ERROR;
            case VISIT -> VISIT_UNKNOWN_ERROR;
        });
    }
}
