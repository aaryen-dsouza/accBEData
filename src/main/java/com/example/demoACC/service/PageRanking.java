package com.example.demoACC.service;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.example.demoACC.dto.PageRankingRequest;


@Service
public class PageRanking {

    @PostConstruct
    public void init() {}
    
    public List<Map<String, Integer>> pageRanking(PageRankingRequest keyword) {

        String[] filePaths = {
            "/fido-phone.csv",
            "/fido-plans.csv",
            "/freedom-mobile.csv",
            "/freedom-plans.csv",
            "/koodo-mobile.csv",
            "/koodo-plans.csv",
            "/rogers.csv",
            "/t-mobile.csv"
        };
    
        // Page names corresponding to the CSV files
        String[] pages = {
            "https://www.fido.ca/phones/?icid=F_WIR_CNV_MXDFRS", 
            "https://www.fido.ca/phones/bring-your-own-device?icid=F_WIR_CNV_GRM6LG&flowType=byod",
            "https://shop.freedommobile.ca/en-CA/", 
            "https://shop.freedommobile.ca/en-CA/plans",
            "https://www.koodomobile.com/en/phones?INTCMP=KMNew_NavMenu_Shop_Phones", 
            "https://www.koodomobile.com/en/rate-plans?INTCMP=KMNew_NavMenu_Shop_Plans",
            "https://www.rogers.com/plans?icid=R_WIR_CMH_6WMCMZ", 
            "https://www.metrobyt-mobile.com/phone-plans?INTNAV=tNav%3APlans"
        };
    
        List<TreeMap<String, Integer>> treeMaps = new ArrayList<>();
    
        // Read and parse each CSV file
        for (String filePath : filePaths) {
            String fileContent = readFile(filePath);
            TreeMap<String, Integer> wordFrequencyMap = parsePage(fileContent);
            treeMaps.add(wordFrequencyMap);
        }
    
        // Create a list to store the frequencies with page names
        List<Map<String, Integer>> rankedResults = new ArrayList<>();
        boolean anyNonZeroFrequency = false;
    
        for (int i = 0; i < pages.length; i++) {
            int frequency = treeMaps.get(i).getOrDefault(keyword.getWord().toLowerCase(), 0);
            if (frequency > 0) {
                anyNonZeroFrequency = true;
            }
            Map<String, Integer> pageFrequency = new HashMap<>();
            pageFrequency.put(pages[i], frequency);
            rankedResults.add(pageFrequency);
        }
    
        // Sort the list by frequency in descending order
        rankedResults.sort((map1, map2) -> {
            Integer freq1 = map1.values().iterator().next();
            Integer freq2 = map2.values().iterator().next();
            return freq2.compareTo(freq1);
        });
    
        // If all frequencies are zero, return an empty list
        if (!anyNonZeroFrequency) {
            List<Map<String, Integer>> emptyList = new ArrayList<>();
            return emptyList;
        }
    
        return rankedResults;
    }
    
    // Method to parse a page and populate a TreeMap with word frequencies
    public static TreeMap<String, Integer> parsePage(String text) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        String[] words = text.split("\\W+");
        for (String word : words) {
            if (!word.isEmpty()) {
                treeMap.put(word.toLowerCase(), treeMap.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }
        return treeMap;
    }

    // Method to read file content
    private String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
