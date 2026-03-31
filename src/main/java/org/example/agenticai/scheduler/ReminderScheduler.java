package org.example.agenticai.scheduler;

import org.example.agenticai.model.Reminder;
import org.example.agenticai.service.EmailService;
import org.example.agenticai.service.ReminderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Iterator;

@Component
public class ReminderScheduler {

    private final ReminderService reminderService;
    private final EmailService emailService;

    @Value("${email.reminder.recipient:gamerkrrish007@gmail.com}")
    private String reminderEmail;

    public ReminderScheduler(ReminderService reminderService, EmailService emailService) {
        this.reminderService = reminderService;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 30000) // every 30 sec
    public void checkReminders() {

        System.out.println("⏰ [ReminderScheduler] Running... Total reminders: " + reminderService.getReminders().size());
        System.out.println("⏰ [ReminderScheduler] Current time: " + LocalDateTime.now());

        Iterator<Reminder> iterator = reminderService.getReminders().iterator();

        while (iterator.hasNext()) {

            Reminder reminder = iterator.next();
            
            System.out.println("⏰ [ReminderScheduler] Checking reminder - Message: " + reminder.getMessage() + " | Time: " + reminder.getTime());

            if (LocalDateTime.now().isAfter(reminder.getTime())) {

                String message = reminder.getMessage();

                // ✅ Console log
                System.out.println("🔔 REMINDER TRIGGERED: " + message);

                // ✅ Enhanced email message
                String emailBody = "⏰ Reminder: " + message;

                try {
                    emailService.sendEmail(
                            reminderEmail,
                            "Reminder Alert 🔔",
                            emailBody
                    );
                    System.out.println("✅ [ReminderScheduler] Email sent for: " + message);
                } catch (Exception e) {
                    System.out.println("❌ [ReminderScheduler] Error sending email: " + e.getMessage());
                    e.printStackTrace();
                }

                iterator.remove();
            } else {
                System.out.println("⏰ [ReminderScheduler] Reminder not due yet. Current: " + LocalDateTime.now() + " | Scheduled: " + reminder.getTime());
            }
        }
    }
}