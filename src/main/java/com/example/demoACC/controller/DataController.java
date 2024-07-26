package com.example.demoACC.controller;

import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.example.demoACC.service.CsvService;
import com.example.demoACC.service.EmailService;
import com.example.demoACC.service.ProcessedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @PostMapping("/extract-emails")
    public ProcessedData extractEmails(@RequestParam("file") MultipartFile file, @RequestParam("domain") String domain) throws IOException {
        // Save the file locally
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);

        // Process the file
        return emailService.processFile(convFile.getAbsolutePath(), domain);
    }

}
