package com.example.demoACC.controller;

import com.example.demoACC.dto.FrequencyCountRequest;
import com.example.demoACC.dto.PageRankingRequest;
import com.example.demoACC.dto.SpellCheckingRequest;
import com.example.demoACC.dto.WordCompletionRequest;
import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.example.demoACC.model.SpellingSuggestions;
import com.example.demoACC.service.CsvService;
import com.example.demoACC.service.FrequencyCount;
import com.example.demoACC.service.PageRanking;
import com.example.demoACC.service.SpellChecker;
import com.example.demoACC.service.WordCompleter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DataController {

    @Autowired
    private CsvService csvService;

    @Autowired
    private WordCompleter wordCompletionService;

    @Autowired
    private PageRanking pageRankingService;

    @Autowired
    private SpellChecker spellCheckerService;

    @Autowired
    private FrequencyCount frequencyCount;

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
}
