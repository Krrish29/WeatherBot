package org.example.agenticai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeatherMonitorService {

    private static final String API_KEY = "e7841bb6a37c09d023dab11f631c106c";

    public double getTemperature(String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + API_KEY + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            // ✅ Check if response is null
            if (response == null || !response.containsKey("main")) {
                System.out.println("❌ Invalid response for city: " + city);
                return -1;
            }

            Map<String, Object> main = (Map<String, Object>) response.get("main");

            Object tempObj = main.get("temp");

            if (tempObj == null) {
                System.out.println("❌ Temperature not found");
                return -1;
            }

            return Double.parseDouble(tempObj.toString());

        } catch (Exception e) {
            System.out.println("❌ Weather fetch failed for: " + city);
            e.printStackTrace(); // 🔥 IMPORTANT for debugging
            return -1;
        }
    }
}