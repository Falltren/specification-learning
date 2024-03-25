package com.example.specificationlearning.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class NewsRs {

    private Long id;
    private String title;
    private String text;
    private Instant date;
}
