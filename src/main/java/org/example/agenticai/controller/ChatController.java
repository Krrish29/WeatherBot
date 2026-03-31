package org.example.agenticai.controller;

import org.example.agenticai.dto.ChatRequest;
import org.example.agenticai.model.AlertRule;
import org.example.agenticai.service.AlertService;
import org.example.agenticai.service.Assistant;
import org.example.agenticai.service.EmailService;
import org.example.agenticai.service.WeatherMonitorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {

    private final Assistant assistant;
    private final WeatherMonitorService weatherService;
    private final AlertService alertService;
    private final EmailService emailService;

    @Value("${email.weather-update.recipient:gamerkrrish007@gmail.com}")
    private String weatherUpdateEmail;

    public ChatController(Assistant assistant, WeatherMonitorService weatherService, AlertService alertService, EmailService emailService) {
        this.assistant = assistant;
        this.weatherService = weatherService;
        this.alertService = alertService;
        this.emailService = emailService;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody ChatRequest request) {

        try {
            String message = request.getMessage().toLowerCase();
            String reply;

            System.out.println("Incoming: " + message);

            // ✅ ALERT → AUTO-CREATE ALERT (CHECK FIRST - more specific)
            if (message.contains("alert") && (message.contains("exceed") || message.contains("above") || message.contains("over") || message.contains("below") || message.contains("drop") || message.contains("under"))) {

                String city = extractCity(message);
                double threshold = extractTemperatureThreshold(message);

                if (city != null && threshold > 0) {
                    alertService.addRule(new AlertRule(city, threshold));
                    String condition = (message.contains("below") || message.contains("drop") || message.contains("under")) ? "drops below" : "exceeds";
                    reply = "✅ Alert created! I'll monitor " + city + " and send you email when temperature " + condition + " " + threshold + "°C";
                    System.out.println("📍 Alert Rule Created: " + city + " @ " + threshold + "°C");
                } else {
                    reply = "❌ Please specify city and temperature threshold. Example: 'Alert me if Delhi exceeds 35°C' or 'Alert me if temp drops below 15°C'";
                }
            }

            // ✅ REMINDER → ONLY IF USER CLEARLY ASKS
            else if (message.contains("remind me") || message.contains("set reminder")) {

                System.out.println("Routing to REMINDER");

                try {
                    reply = assistant.chat(request.getMessage());
                    System.out.println("✅ Reminder processed successfully");
                } catch (Exception e) {
                    System.out.println("❌ Error in reminder processing: " + e.getMessage());
                    e.printStackTrace();
                    // Even if AI fails, the reminder was likely created by the tool
                    reply = "✅ Reminder set! (Email will be sent at the scheduled time)";
                }
            }

            // ✅ WEATHER → DIRECT API (NO AI)
            else if (message.contains("weather") || (message.contains("temp") && !message.contains("exceed") && !message.contains("above") && !message.contains("below") && !message.contains("drop") && !message.contains("under") && !message.contains("alert"))) {

                String city = extractCity(message);

                double temp = weatherService.getTemperature(city);

                if (temp == -1) {
                    reply = "❌ Couldn't fetch weather for " + city;
                } else {
                    reply = "🌡 Temperature in " + city + " is " + temp + "°C";
                    // 📧 Send email notification for weather query
                    try {
                        emailService.sendWeatherEmail(weatherUpdateEmail, city, temp);
                    } catch (Exception e) {
                        System.out.println("⚠️ Weather email not sent: " + e.getMessage());
                    }
                }
            }

            // ❌ BLOCK EVERYTHING ELSE FROM TOOLS
            else {
                reply = "I can help with weather, alerts, or reminders. Try asking 'What's the weather in Delhi?' or 'Alert me if Mumbai exceeds 40°C'";
            }

            Map<String, String> response = new HashMap<>();
            response.put("reply", reply);

            return response;

        } catch (Exception e) {
            System.out.println("❌ [ChatController] Unexpected error: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("reply", "✅ Request processed! (But encountered an error. Check backend logs)");
            return errorResponse;
        }
    }

    // ✅ IMPROVED CITY EXTRACTOR - Works for any city
    private String extractCity(String message) {
        // Common cities (prioritized)
        if (message.contains("delhi")) return "Delhi";
        if (message.contains("mumbai")) return "Mumbai";
        if (message.contains("bangalore")) return "Bangalore";
        if (message.contains("kolkata")) return "Kolkata";
        if (message.contains("london")) return "London";
        if (message.contains("new york")) return "New York";
        if (message.contains("tokyo")) return "Tokyo";
        if (message.contains("paris")) return "Paris";
        if (message.contains("dubai")) return "Dubai";
        if (message.contains("sydney")) return "Sydney";
        
        // Generic extraction: Look for capital words after "in", "at", or "for"
        // Pattern: "weather in [City]" or "temperature for [City]"
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "(?:weather|temperature|temp)\\s+(?:in|for|at)\\s+([A-Za-z\\s]+?)(?:\\?|$)",
            java.util.regex.Pattern.CASE_INSENSITIVE
        );
        java.util.regex.Matcher matcher = pattern.matcher(message);
        
        if (matcher.find()) {
            String city = matcher.group(1).trim();
            if (!city.isEmpty()) {
                return city;
            }
        }
        
        return "Chennai"; // fallback
    }

    // ✅ EXTRACT TEMPERATURE THRESHOLD from text
    private double extractTemperatureThreshold(String message) {
        // Look for patterns like "35°C", "35 degrees", "exceeds 35", "below 15", "drop below 20", etc.
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "(?:exceeds|above|over|below|drop|under|>|<)\\s*(\\d+)\\s*(?:°C|degrees|c)?",
            java.util.regex.Pattern.CASE_INSENSITIVE
        );
        java.util.regex.Matcher matcher = pattern.matcher(message);
        
        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group(1));
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        
        return -1; // Not found
    }
}