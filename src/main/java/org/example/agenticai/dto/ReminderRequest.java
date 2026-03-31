package org.example.agenticai.dto;

import lombok.Data;

@Data
public class ReminderRequest {
    private String message;
    private String time;
}
