package com.mj.visit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckInDto {
    private String guestId;
    private Short roomNumber;
    private LocalDateTime departure;
}
