package com.mj.exception;

import lombok.Getter;

@Getter
public enum GlobalErrorCode {
    GUEST_UNKNOWN_ERROR("GUEST_1000"),
    ROOM_UNKNOWN_ERROR("ROOM_1000"),
    VISIT_UNKNOWN_ERROR("VISIT_1000");

    private final String code;

    GlobalErrorCode(String code) {
        this.code = code;
    }

    static GlobalErrorCode getUnknownError(ServiceType serviceType) {
        return (switch (serviceType) {
            case GUEST -> GUEST_UNKNOWN_ERROR;
            case ROOM -> ROOM_UNKNOWN_ERROR;
            case VISIT -> VISIT_UNKNOWN_ERROR;
        });
    }
}
