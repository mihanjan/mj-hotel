package com.mj.visit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
    private String id;
    private String fullName;
    private String passport;
    private String email;
    private Double discountPercent;
}
