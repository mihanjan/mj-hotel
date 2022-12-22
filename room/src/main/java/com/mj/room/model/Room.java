package com.mj.room.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, unique = true)
    @Min(100)
    @Max(999)
    @NotNull
    private Short number;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    @NotNull
    private RoomType roomType;

    @Column(updatable = false)
    @Min(1)
    @Max(5)
    @NotNull
    private Byte maxPersons;

    @Column(updatable = false)
    @Min(100)
    @NotNull
    private Double pricePerHour;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private boolean occupied;
}
