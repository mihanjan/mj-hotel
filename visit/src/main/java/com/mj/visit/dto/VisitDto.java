package com.mj.visit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitDto {
    private String guestId;
    private Short roomNumber;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private Double cost;
    private boolean active;
}
