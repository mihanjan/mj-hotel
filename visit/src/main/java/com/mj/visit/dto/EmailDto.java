package com.mj.visit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDto {
    private String address;
    private String subject;
    private String text;
}
