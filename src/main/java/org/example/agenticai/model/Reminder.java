package org.example.agenticai.model;

import java.time.LocalDateTime;

public class Reminder {

    private String message;
    private LocalDateTime time;

    public Reminder(String message, LocalDateTime time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }
}