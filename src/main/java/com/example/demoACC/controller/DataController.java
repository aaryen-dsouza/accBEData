package com.example.demoACC.controller;

import com.example.demoACC.dto.FrequencyCountRequest;
import com.example.demoACC.dto.PageRankingRequest;
import com.example.demoACC.dto.SearchFrequencyRequest;
import com.example.demoACC.dto.SpellCheckingRequest;
import com.example.demoACC.dto.WordCompletionRequest;
import com.example.demoACC.model.BestPlan;
import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.example.demoACC.model.ProcessedData;
import com.example.demoACC.service.BestPlanService;
import com.example.demoACC.service.CsvService;
import com.example.demoACC.service.ProcessService;
import com.example.demoACC.service.SearchFrequency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.example.demoACC.model.SpellingSuggestions;
import com.example.demoACC.service.FrequencyCount;
import com.example.demoACC.service.PageRanking;
import com.example.demoACC.service.SpellChecker;
import com.example.demoACC.service.WordCompleter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DataController {

    @Autowired
    private CsvService csvService;
    
    @Autowired
    private ProcessService processService;

    @Autowired
    private WordCompleter wordCompletionService;

    @Autowired
    private PageRanking pageRankingService;

    @Autowired
    private SpellChecker spellCheckerService;

    @Autowired
    private FrequencyCount frequencyCount;

    @Autowired
    private SearchFrequency searchFrequency;

    @Autowired
    private BestPlanService bestPlanService;

    @GetMapping("/fido-mobile-plans")
    public List<Mobile> getFidoMobilePlans() {
        return csvService.getFidoMobileOffers();
    }

    @GetMapping("/fido-sim-plans")
    public List<Plan> getFidoSimPlans() {
        return csvService.getFidoPlanOffers();
    }

    @GetMapping("/freedom-mobile-plans")
    public List<Mobile> getFreedomMobilePlans() {
        return csvService.getFreedomMobilePlans();
    }

    @GetMapping("/freedom-sim-plans")
    public List<Plan> getFreedomSimPlans() {
        return csvService.getFreedomSimPlans();
    }

    @GetMapping("/koodo-mobile-plans")
    public List<Mobile> getKoodoMobilePlans() {
        return csvService.getKoodoMobilePlans();
    }

    @GetMapping("/koodo-sim-plans")
    public List<Plan> getKoodoSimPlans() {
        return csvService.getKoodoSimPlans();
    }

    @PostMapping("/word-completer")
    public List<String> wordCompleter(@RequestBody WordCompletionRequest request) {
        return wordCompletionService.completeWord(request);
    }

    @PostMapping("/page-ranking")
    public List<Map<String, Integer>> pageRanking(@RequestBody PageRankingRequest request) {
        return pageRankingService.pageRanking(request);
    }

    @PostMapping("/spell-checker")
    public SpellingSuggestions pageRanking(@RequestBody SpellCheckingRequest request) {
        return spellCheckerService.getSpellCheck(request);
    }

    @PostMapping("frequency-count")
    public List<Map.Entry<String, Integer>> getFrequencyCount(@RequestBody FrequencyCountRequest request) {
        return frequencyCount.getFrequencyCount(request);
    }

    @PostMapping("add-search-frequency")
    public boolean addSearchFrequency(@RequestBody SearchFrequencyRequest request) {
        return searchFrequency.addSearchFrequency(request);
    }

    @GetMapping("get-search-frequency")
    public List<Map<String, Integer>> getSearchFrequency() {
        return searchFrequency.getTopSearches();
    }

    @GetMapping("best-plans")
    public List<BestPlan> getBestPlan() {
        return bestPlanService.getBestPlan();
    }

    @PostMapping("/extract-data-from-text")
    public ResponseEntity<ProcessedData> extractDataFromText(@RequestParam("file") MultipartFile file, @RequestParam("domain") String domain) {
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
