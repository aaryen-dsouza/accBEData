package com.example.demoACC.controller;

import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.example.demoACC.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private CsvService csvService;

    @GetMapping("/mobilePlans")
    public List<Mobile> getMobilePlans() {
        return csvService.getMobiles();
    }

    @GetMapping("/simPlans")
    public List<Plan> getSimPlans() {
        return csvService.getPlans();
    }
}
