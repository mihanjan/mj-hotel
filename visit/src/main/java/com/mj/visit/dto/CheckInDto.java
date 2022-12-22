package com.mj.visit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitDto {
    private String fullName;
    private String passport;
    private String email;
    private Short roomNumber;
    private LocalDateTime departure;
}
