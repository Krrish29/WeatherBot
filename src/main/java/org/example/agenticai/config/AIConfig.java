package org.example.agenticai.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.example.agenticai.service.Assistant;
import org.example.agenticai.tools.WeatherTool;
import org.example.agenticai.tools.ReminderTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean
    public Assistant assistant(WeatherTool weatherTool, ReminderTool reminderTool) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey("YOUR_GROQ_API_KEY_HERE")
                .baseUrl("https://api.groq.com/openai/v1")
                .modelName("llama-3.1-8b-instant")
                .build();

        return AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(weatherTool, reminderTool) // ✅ FIXED
                .build();
    }
}