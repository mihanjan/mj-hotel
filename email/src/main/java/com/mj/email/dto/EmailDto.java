package com.mj.email.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String address;
    private String subject;
    private String text;
}
