package com.mj.visit.dto;

import lombok.Data;

@Data
public class RoomDto {
    private Short number;
    private Double pricePerHour;
    private boolean occupied;
}
