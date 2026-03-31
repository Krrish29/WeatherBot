package org.example.agenticai.tools;

import dev.langchain4j.agent.tool.Tool;
import org.example.agenticai.model.Reminder;
import org.example.agenticai.service.ReminderService; // ✅ FIXED
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReminderTool {

    private final ReminderService reminderService;

    public ReminderTool(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @Tool("""
        Set a reminder with message and time.
        Time MUST be in ISO format: yyyy-MM-ddTHH:mm:ss
        Example: 2026-03-25T20:00:00
    """)
    public String setReminder(String message, String time) {

        try {
            LocalDateTime reminderTime = LocalDateTime.parse(time);

            reminderService.addReminder(new Reminder(message, reminderTime));

            return "Reminder set for " + message + " at " + reminderTime;

        } catch (Exception e) {
            return "Invalid time format. Please provide time like 2026-03-25T20:00:00";
        }
    }
}