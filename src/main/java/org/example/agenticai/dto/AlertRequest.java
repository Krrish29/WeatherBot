package org.example.agenticai.dto;

import lombok.Data;

@Data
public class AlertRequest {
    private String city;
    private double threshold;
}
