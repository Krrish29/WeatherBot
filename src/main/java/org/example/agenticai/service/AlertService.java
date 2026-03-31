package org.example.agenticai.service;

import org.example.agenticai.model.AlertRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private final List<AlertRule> rules = new ArrayList<>();

    public void addRule(AlertRule rule) {
        rules.add(rule);
        System.out.println("✅ Rule added for " + rule.getCity());
    }

    public List<AlertRule> getRules() {
        return rules;
    }
}