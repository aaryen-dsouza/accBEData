package com.example.demoACC.model;

import lombok.Data;
import java.util.List;

@Data
public class SpellingSuggestions {
    private boolean isValid;
    private List<Suggestion> suggestedWords;
}
