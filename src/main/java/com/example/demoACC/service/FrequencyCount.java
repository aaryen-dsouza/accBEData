// Import statements
package com.example.demoACC.service;

import java.io.*; // Import classes used to implement I/O operations
import java.util.*; // Import this class for understanding data structures
import java.util.regex.*; // Import for regular expressions
import org.springframework.stereotype.Service;
import com.example.demoACC.dto.FrequencyCountRequest;

@Service
public class FrequencyCount { 
    public List<Map.Entry<String, Integer>> getFrequencyCount(FrequencyCountRequest request) {
        // List of file paths relative to the resources directory
        List<String> filePaths = Arrays.asList(
            "fido-phone.csv",
            "fido-plans.csv",
            "freedom-mobile.csv",
            "freedom-plans.csv",
            "koodo-mobile.csv",
            "koodo-plans.csv",
            "t-mobile.csv",
            "rogers.csv"
        );

        String[] pages = {
            "fido-phones-page", 
            "fido-plan-page",
            "freedom-phone-page", 
            "freedom-plans-page",
            "koodo-phone-page", 
            "koodo-plans-page",
            "t-mobile-home-page",
            "rogers-home-page"
        };

        // Array to store frequency counts for each file
        Map<String, Integer>[] freqCounts = new Map[filePaths.size()];
        for (int i = 0; i < freqCounts.length; i++) {
            freqCounts[i] = new HashMap<>();
        }

        // Pattern to identify URLs
        String urlPattern = "(https?://[\\w-]+(\\.[\\w-]+)+(/\\S*)?)";
        Pattern pattern = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);

        // Processing each file
        for (int i = 0; i < filePaths.size(); i++) {
            String filePath = filePaths.get(i);
            List<String> lines = readFile(filePath);

            // Counting word frequencies for the current file
            for (String line : lines) {
                // Find URLs and handle them separately
                Matcher urlMatcher = pattern.matcher(line);
                while (urlMatcher.find()) {
                    String url = urlMatcher.group();
                    freqCounts[i].put(url, freqCounts[i].getOrDefault(url, 0) + 1);
                }

                // Remove URLs from the line for further processing
                String lineWithoutUrls = urlMatcher.replaceAll("");

                // Tokenize the remaining line into words using regex
                String[] words = lineWithoutUrls.split("\\W+");
                for (String word : words) {
                    word = word.trim();
                    if (!word.isEmpty()) {
                        word = word.toLowerCase();
                        freqCounts[i].put(word, freqCounts[i].getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        // Collect results and sort by frequency in descending order
        List<Map.Entry<String, Integer>> sortedResults = new ArrayList<>();
        for (int i = 0; i < filePaths.size(); i++) {
            String fileName = pages[i];
            int frequency = freqCounts[i].getOrDefault(request.getWord(), 0);
            sortedResults.add(new AbstractMap.SimpleEntry<>(fileName, frequency));
        }

        // Sort the list based on frequency in descending order
        sortedResults.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));
        return sortedResults;
    }

    // Method to read file content using InputStreamReader
    private List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/" + filePath)) {
            if (inputStream == null) {
                System.err.println("File not found: " + filePath);
                return lines; // Return empty list or handle as needed
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }
    
}
