package com.example.specificationlearning.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;
    @NotEmpty(message = "поле title должно быть заполнено")
    private String title;
}
