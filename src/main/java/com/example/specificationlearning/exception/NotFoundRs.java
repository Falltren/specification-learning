package com.example.specificationlearning.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotFoundRs {

    private String message;
}
