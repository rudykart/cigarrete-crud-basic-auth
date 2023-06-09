package com.rudykart.cigarrete.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BrandDto {
    @NotEmpty(message = "input can't empty")
    @NotNull(message = "input can't null")
    @NotBlank(message = "input can't not blank")
    private String name;
}
