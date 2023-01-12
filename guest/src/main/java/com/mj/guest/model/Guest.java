package com.mj.guest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document(collection = "users")
public class Guest {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @NotNull
    @NotBlank
    private String fullName;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    @Indexed(unique = true)
    private String passport;

    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull
    private double discountPercent = 0;
}
