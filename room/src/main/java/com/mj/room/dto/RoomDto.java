package com.mj.room.dto;

import com.mj.room.model.RoomType;
import lombok.Data;

@Data
public class RoomDto {
    private Short number;
    private RoomType roomType;
    private Byte maxPersons;
    private Double pricePerHour;
    private boolean occupied;
}
