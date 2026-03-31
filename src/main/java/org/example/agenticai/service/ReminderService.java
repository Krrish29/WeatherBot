package org.example.agenticai.service;

import org.example.agenticai.model.Reminder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderService {

    private final List<Reminder> reminders = new ArrayList<>();

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
        System.out.println("✅ Reminder added: " + reminder.getMessage());
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }
}