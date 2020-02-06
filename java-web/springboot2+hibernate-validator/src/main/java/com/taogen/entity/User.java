package com.taogen.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class User
{
    @NotNull
    private String id;

    @NotNull
    private String username;

    @Min(1)
    @Max(150)
    private Integer age;

    private Integer gender;
}
