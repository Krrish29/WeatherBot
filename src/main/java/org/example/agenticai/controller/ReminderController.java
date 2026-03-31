package org.example.agenticai.controller;

import org.example.agenticai.dto.ReminderRequest;
import org.example.agenticai.model.Reminder;
import org.example.agenticai.service.ReminderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/reminder")
@CrossOrigin(origins = "*")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public String setReminder(@RequestBody ReminderRequest request) {

        try {
            // Parse the time from "HH:mm" format (from HTML time input)
            LocalTime parsedTime = LocalTime.parse(request.getTime());
            
            // Create a LocalDateTime with today's date and the provided time
            LocalDateTime reminderTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), parsedTime);
            
            // If the time has already passed today, schedule for tomorrow
            if (reminderTime.isBefore(LocalDateTime.now())) {
                reminderTime = reminderTime.plusDays(1);
            }

            reminderService.addReminder(new Reminder(request.getMessage(), reminderTime));
            
            System.out.println("📧 Reminder will be triggered at: " + reminderTime);

            return "✅ Reminder set successfully for " + reminderTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"));
        } catch (Exception e) {
            System.out.println("❌ Reminder error: " + e.getMessage());
            return "❌ Error setting reminder: " + e.getMessage();
        }
    }
}