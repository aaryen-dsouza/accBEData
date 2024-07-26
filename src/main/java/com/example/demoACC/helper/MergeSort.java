package com.example.demoACC.helper;
import java.util.ArrayList;
import java.util.List;

import com.example.demoACC.model.Suggestion;

public class MergeSort {
    public static void sort(List<Suggestion> suggestions) {
        if (suggestions.size() > 1) {
            int mid = suggestions.size() / 2;
            List<Suggestion> left = new ArrayList<>(suggestions.subList(0, mid));
            List<Suggestion> right = new ArrayList<>(suggestions.subList(mid, suggestions.size()));

            sort(left);
            sort(right);

            merge(suggestions, left, right);
        }
    }

    private static void merge(List<Suggestion> suggestions, List<Suggestion> left, List<Suggestion> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).distance <= right.get(j).distance) {
                suggestions.set(k++, left.get(i++));
            } else {
                suggestions.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            suggestions.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            suggestions.set(k++, right.get(j++));
        }
    }
}
