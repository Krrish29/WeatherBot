package org.example.agenticai.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class WeatherTool {

    private static final String API_KEY = "e7841bb6a37c09d023dab11f631c106c";

    @Tool("""
        Get current temperature of a city.
        Input: city name only.
        Output: ONLY temperature result.
    """)
    public String getWeather(String city) {

        String cleanCity = city.replaceAll("[^a-zA-Z ]", "").trim();

        System.out.println("City received: " + city);
        System.out.println("Clean city: " + cleanCity);

        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + cleanCity + "&appid=" + API_KEY + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("main")) {
                return "❌ Weather not available for " + cleanCity;
            }

            Map<String, Object> main = (Map<String, Object>) response.get("main");

            Object tempObj = main.get("temp");

            if (tempObj == null) {
                return "❌ Temperature not found";
            }

            double temp = Double.parseDouble(tempObj.toString());

            // ✅ CLEAN RESPONSE (important)
            return "🌡 Temperature in " + cleanCity + " is " + temp + "°C";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Could not fetch weather for " + cleanCity;
        }
    }
}