// import necessary libraries
package com.example.demoACC.helper;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

// this code helps in generating vocabulary from csv files
public class VocabularyWordCompleter {
    public Map<String, Integer> readFiles(String filePath) {
        String line;
        boolean isFirstLine = true;
        Map<String, Integer> vocabulary = new HashMap<>(); // instantiate a Map to store the vocabulary
        Pattern urlPattern = Pattern.compile(
            "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            Pattern.CASE_INSENSITIVE);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)))) {
            // loop through the CSV files to get the words
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { // if it is the first line skip it as the data in the first line is headers
                    isFirstLine = false;
                    continue;
                }
                String[] cols = line.split(",");
                if (cols.length > 2) {
                    processColumn(cols[0].trim(), vocabulary, urlPattern);
                    processColumn(cols[1].trim(), vocabulary, urlPattern);
                    processColumn(cols[2].trim(), vocabulary, urlPattern);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); // catch and print errors
        }
        return vocabulary;
    }

    private void processColumn(String columnValue, Map<String, Integer> vocabulary, Pattern urlPattern) {
        String[] words = columnValue.split("\\s+"); // split by one or more spaces
        for (String word : words) {
            word = word.toLowerCase().trim(); // convert to lower case and trim
            if (!word.isEmpty() || urlPattern.matcher(word).matches()) {
                vocabulary.put(word, vocabulary.getOrDefault(word, 0) + 1); // add the words to the vocabulary
            }
        }
    }
}
