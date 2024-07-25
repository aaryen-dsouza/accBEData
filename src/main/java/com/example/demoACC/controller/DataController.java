package com.example.demoACC.controller;

import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.example.demoACC.service.CsvService;
import com.example.demoACC.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private CsvService csvService;
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/mobilePlans")
    public List<Mobile> getMobilePlans() {
        return csvService.getMobiles();
    }

    @GetMapping("/simPlans")
    public List<Plan> getSimPlans() {
        return csvService.getPlans();
    }

    @GetMapping("/extract-emails")
    public List<String> extractEmails() {
        String text = "dc.aaryen@gmail.com sharan@gma.com";
        return emailService.extractEmailAddresses(text);
    }

//    @PostMapping("/extract-emails")
//    public List<String> extractEmails(@RequestBody Map<String, String> request) {
//        String text = request.get("text");
//        return emailService.extractEmailAddresses(text);
//    }




}
