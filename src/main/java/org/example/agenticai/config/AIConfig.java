package org.example.agenticai.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.example.agenticai.service.Assistant;
import org.example.agenticai.tools.WeatherTool;
import org.example.agenticai.tools.ReminderTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Value("${groq.api.key:YOUR_GROQ_API_KEY_HERE}")
    private String groqApiKey;

    @Bean
    public Assistant assistant(WeatherTool weatherTool, ReminderTool reminderTool) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(groqApiKey)
                .baseUrl("https://api.groq.com/openai/v1")
                .modelName("llama-3.1-8b-instant")
                .build();

        return AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(weatherTool, reminderTool) // ✅ FIXED
                .build();
    }
}