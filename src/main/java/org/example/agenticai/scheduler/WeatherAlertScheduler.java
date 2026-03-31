package org.example.agenticai.scheduler;

import org.example.agenticai.service.EmailService;
import org.example.agenticai.service.WeatherMonitorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherAlertScheduler {

    private final WeatherMonitorService weatherService;
    private final EmailService emailService;

    private static final double TEMP_THRESHOLD = 25;
    private boolean alertSent = false;

    @Value("${scheduler.enabled:false}")
    private boolean schedulerEnabled;

    @Value("${email.weather-alert.recipient:gamerkrrish007@gmail.com}")
    private String weatherAlertEmail;

    public WeatherAlertScheduler(WeatherMonitorService weatherService,
                                 EmailService emailService) {
        this.weatherService = weatherService;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkWeather() {

        // ✅ STOP scheduler if disabled
        if (!schedulerEnabled) return;

        double temp = weatherService.getTemperature("Chennai");

        if (temp == -1) return;

        System.out.println("🌡 Current Temp: " + temp);

        if (temp > TEMP_THRESHOLD && !alertSent) {

            String message = "🔥 Temperature is too high: " + temp + "°C";

            System.out.println(message);

            emailService.sendEmail(
                    weatherAlertEmail,
                    "Weather Alert ⚠️",
                    message
            );

            alertSent = true;
        }

        if (temp <= TEMP_THRESHOLD) {
            alertSent = false;
        }
    }
}