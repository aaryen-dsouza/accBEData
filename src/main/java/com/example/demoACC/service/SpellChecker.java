package com.example.demoACC.service;
import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demoACC.dto.SpellCheckingRequest;
import com.example.demoACC.helper.CuckooHashTable;
import com.example.demoACC.helper.EditDistance;
import com.example.demoACC.helper.MergeSort;
import com.example.demoACC.helper.VocabularySpellChecking;
import com.example.demoACC.model.SpellingSuggestions;
import com.example.demoACC.model.Suggestion;

@Service
public class SpellChecker {

    public SpellingSuggestions getSpellCheck(SpellCheckingRequest request) {
        String[] filePaths = {"/merged.csv"};  // Add your file paths here
        VocabularySpellChecking checking = new VocabularySpellChecking();
        Set<String> vocabulary = checking.readFiles(filePaths);

        // Part 2: Cuckoo Hashing for Efficient Lookup
        CuckooHashTable hashTable = new CuckooHashTable();
        for (String word : vocabulary) {
            hashTable.insert(word);
        }

        // Part 3: Alternative Word Suggestions
        List<Suggestion> suggestions = new ArrayList<>();
        boolean isValid = hashTable.search(request.getWord());
        suggestions = suggestAlternatives(request.getWord(), vocabulary);
        SpellingSuggestions generatedSuggestion = new SpellingSuggestions();
        generatedSuggestion.setValid(isValid);
        generatedSuggestion.setSuggestedWords(suggestions);
        return generatedSuggestion;
    }

    private static List<Suggestion> suggestAlternatives(String word, Set<String> vocabulary) {
        List<Suggestion> suggestions = new ArrayList<>();
        for (String vocabWord : vocabulary) {
            int distance = EditDistance.calculate(word, vocabWord);
            suggestions.add(new Suggestion(vocabWord, distance));
        }
        MergeSort.sort(suggestions);
        return suggestions.subList(0, Math.min(3, suggestions.size()));
    }
}