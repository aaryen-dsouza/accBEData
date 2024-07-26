package com.example.demoACC.helper;

import java.io.*;
import java.util.*;

public class VocabularySpellChecking {
    public Set<String> readFiles(String[] filePaths) {
        Set<String> vocabulary = new HashSet<>();
        for (String filePath : filePaths) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (filePath.endsWith(".csv")) {
                        String[] parts = line.split(",");
                        // System.out.println("Parts: " + parts);
                        for (String part : parts) {
                            // System.out.println("Part: " + part);
                            processAndAddWords(part, vocabulary);

                        }
                    } else { // Split line by whitespace for text files
                        processAndAddWords(line, vocabulary);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // System.out.println(vocabulary);
            // System.out.println("Size: " + vocabulary.size());
        }

        return vocabulary;
    }

    private static void processAndAddWords(String text, Set<String> vocabulary) {
        // Split the text by any whitespace
        String[] words = text.split("\\s+");
// System.out.println("Words: " + Arrays.toString(words));
        // Process each word
        for (String word : words) {
            // System.out.println("Word: " + word);
            // Remove non-alphabetic characters and convert to lowercase
            String cleanedWord = cleanWord(word);
            if (!cleanedWord.isEmpty()) {
                vocabulary.add(cleanedWord);
            }
        }
    }

    private static String cleanWord(String word) {
        return word.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }
}
