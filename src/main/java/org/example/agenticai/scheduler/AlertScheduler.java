package org.example.agenticai.scheduler;

import org.example.agenticai.model.AlertRule;
import org.example.agenticai.service.AlertService;
import org.example.agenticai.service.EmailService;
import org.example.agenticai.tools.WeatherTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AlertScheduler {

    private final AlertService alertService;
    private final WeatherTool weatherTool;
    private final EmailService emailService;
    
    // Track which alerts have already sent notifications (to prevent spam)
    private final Map<String, Boolean> alertNotified = new HashMap<>();

    @Value("${email.temperature-alert.recipient:gamerkrrish007@gmail.com}")
    private String alertEmail;

    public AlertScheduler(AlertService alertService, WeatherTool weatherTool, EmailService emailService) {
        this.alertService = alertService;
        this.weatherTool = weatherTool;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void checkAlerts() {

        for (AlertRule rule : alertService.getRules()) {

            String response = weatherTool.getWeather(rule.getCity());

            double temp = extractTemp(response);
            
            String alertKey = rule.getCity() + "_" + rule.getTemperatureThreshold();

            // ✅ If temperature exceeds threshold and we haven't notified yet
            if (temp > rule.getTemperatureThreshold() && !alertNotified.getOrDefault(alertKey, false)) {
                
                // Send beautiful alert email with all details
                emailService.sendAlertEmail(
                        alertEmail,
                        rule.getCity(),
                        temp,
                        rule.getTemperatureThreshold(),
                        temp - rule.getTemperatureThreshold()
                );
                
                System.out.println("✉️ EMAIL SENT: Alert for " + rule.getCity() + " at " + temp + "°C");
                
                // Mark as notified
                alertNotified.put(alertKey, true);
            }
            
            // ✅ Reset notification when temperature goes back below threshold
            if (temp <= rule.getTemperatureThreshold() && alertNotified.getOrDefault(alertKey, false)) {
                alertNotified.put(alertKey, false);
                System.out.println("🔄 Alert reset for " + rule.getCity() + " - Temperature back to normal");
            }
        }
    }

    private double extractTemp(String response) {
        return Double.parseDouble(response.replaceAll("[^0-9.]", ""));
    }
}