package com.rudykart.cigarrete.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CigarreteDto {

    @NotEmpty(message = "input can't empty")
    @NotNull(message = "input can't null")
    @NotBlank(message = "input can't not blank")
    private String name;
    @NotNull(message = "input can't null")
    @Min(value = 0, message = "iput must greater than 0")
    private int value;
    @NotNull(message = "input can't null")
    @Min(value = 0, message = "iput must greater than 0")
    private Double price;

    private Long brandId;
}
