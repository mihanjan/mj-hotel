package com.mj.guest.dto;

import lombok.Data;

@Data
public class GuestDto {
    private String id;
    private String fullName;
    private String passport;
    private String email;
    private Double discountPercent;
}
