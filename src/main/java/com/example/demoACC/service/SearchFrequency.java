package com.example.demoACC.service;

import java.io.*;
import java.util.*;

import com.example.demoACC.dto.SearchFrequencyRequest;
import com.example.demoACC.helper.AVLTree;
import com.example.demoACC.model.AVLNode;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class SearchFrequency {
    private AVLTree avlTree = new AVLTree();
    private LinkedList<String> queryLog = new LinkedList<>();
    private static final String filePath = "search-frequency.csv";

    public SearchFrequency() {
        loadQueriesFromCSV();
    }

    @PostConstruct
    private void loadQueriesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                avlTree.insert(line);
                queryLog.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from CSV file: " + e.getMessage());
        }
    }

    public boolean addSearchFrequency(SearchFrequencyRequest request) {
        if (!request.getWord().equals("")) {
            avlTree.insert(request.getWord());
            queryLog.add(request.getWord());
            writeQueryToCSV(request.getWord());
            return true;
        }
        return false;
    }

    public List<Map<String, Integer>> getTopSearches() {
        List<AVLNode> topSearches = new ArrayList<>();
        avlTree.getTopSearches(topSearches);
        topSearches.sort((a, b) -> b.frequency - a.frequency);

        List<Map<String, Integer>> result = new ArrayList<>();
        for (AVLNode node : topSearches) {
            Map<String, Integer> entry = new HashMap<>();
            entry.put(node.key, node.frequency);
            result.add(entry);
        }
        return result;
    }

    private void writeQueryToCSV(String query) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(query + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
