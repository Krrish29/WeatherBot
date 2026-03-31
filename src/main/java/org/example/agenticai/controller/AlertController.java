package org.example.agenticai.controller;

import org.example.agenticai.dto.AlertRequest;
import org.example.agenticai.model.AlertRule;
import org.example.agenticai.service.AlertService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alert")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public String setAlert(@RequestBody AlertRequest request) {

        alertService.addRule(new AlertRule(request.getCity(), request.getThreshold()));

        return "Alert set for " + request.getCity() + " above " + request.getThreshold() + "°C";
    }
}