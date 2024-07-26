package com.example.demoACC.service;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.example.demoACC.dto.WordCompletionRequest;
import com.example.demoACC.helper.VocabularyWordCompleter;

@Service
public class WordCompleter {

    @PostConstruct
    public void init() {}

    public List<String> completeWord(WordCompletionRequest request) {
        if (request.getPrefix().equals("")) return new ArrayList<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        VocabularyWordCompleter vocabularyWordCompleter = new VocabularyWordCompleter();
        treeMap.putAll(vocabularyWordCompleter.readFiles("/merged.csv"));
        List<String> results = new ArrayList<>(); // List to store words that match the prefix
        NavigableMap<String, Integer> tailMap = treeMap.tailMap(request.getPrefix(), true);

        // Iterate over the entries in the tailMap
        for (Map.Entry<String, Integer> entry : tailMap.entrySet()) {
            if (!entry.getKey().startsWith(request.getPrefix())) break;
            results.add(entry.getKey());
        }

        // Create a min-heap to store the top k suggestions
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        // Iterate over the results list
        for (String word : results) {
            Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(word, treeMap.get(word));
            minHeap.offer(entry);
            if (minHeap.size() > request.getLimit()) {
                minHeap.poll();
            }
        }

        // List to store the top k suggestions
        List<String> topSuggestions = new ArrayList<>();

        // Extract elements from the min-heap and add them to the topSuggestions list
        while (!minHeap.isEmpty()) {
            topSuggestions.add(minHeap.poll().getKey());
        }

        Collections.reverse(topSuggestions); // Reverse the list to have the highest frequency words first
        return topSuggestions;
    }
}
