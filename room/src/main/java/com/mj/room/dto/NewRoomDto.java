package com.mj.room.dto;

import com.mj.room.model.RoomType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class NewRoomDto {

    @NotNull
    @Min(100)
    @Max(999)
    private Short number;

    @NotNull
    private RoomType roomType;

    @NotNull
    @Min(1)
    @Max(5)
    private Byte maxPersons;

    @NotNull
    @Min(100)
    private Double pricePerHour;
}
