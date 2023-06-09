package com.rudykart.cigarrete.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValidateResponse extends StatusResponse {

    private LocalDateTime timestamp;
    private Map<String, List<String>> errors;
}