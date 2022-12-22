package com.mj.clients.guest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GuestDto {
    private String id;
    private String fullName;
    private String passportId;
    private String email;
    private Double discountPercent;
}
