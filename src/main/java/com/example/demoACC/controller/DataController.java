package com.example.demoACC.controller;

import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.example.demoACC.model.ProcessedData;
import com.example.demoACC.service.CsvService;
import com.example.demoACC.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private CsvService csvService;
    
    @Autowired
    private ProcessService processService;

    @GetMapping("/mobilePlans")
    public List<Mobile> getMobilePlans() {
        return csvService.getMobiles();
    }

    @GetMapping("/simPlans")
    public List<Plan> getSimPlans() {
        return csvService.getPlans();
    }

    @PostMapping("/extract-emails")
    public ResponseEntity<ProcessedData> extractEmails(@RequestParam("file") MultipartFile file, @RequestParam("domain") String domain) {
        try {
            // Save the file locally
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);

            // Process the file
            ProcessedData processedData = processService.processFile(convFile.getAbsolutePath(), domain);
            return new ResponseEntity<>(processedData, HttpStatus.OK);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error processing file", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", e);
        }
    }
}
