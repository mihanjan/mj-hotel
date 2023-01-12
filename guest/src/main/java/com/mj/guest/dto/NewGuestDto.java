package com.mj.guest.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewGuestDto {

    @NotNull
    @NotBlank
    private String fullName;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    private String passport;

    @Email
    private String email;
}
