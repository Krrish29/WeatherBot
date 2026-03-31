package org.example.agenticai;

import org.example.agenticai.config.SSLFix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AgenticAiApplication {

    public static void main(String[] args) {
        SSLFix.disableSSLVerification();
        SpringApplication.run(AgenticAiApplication.class, args);
    }
}

