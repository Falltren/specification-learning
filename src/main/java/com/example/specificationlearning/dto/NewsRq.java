package com.example.specificationlearning.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewsRq {

    private Long id;
    @NotEmpty(message = "поле title не должно быть пустым")
    private String title;
    @NotEmpty(message = "поле text не должно быть пустым")
    private String text;
    @NotEmpty(message = "поле category не должно быть пустым")
    private String category;
}
