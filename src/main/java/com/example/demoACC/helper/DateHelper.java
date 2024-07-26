package com.example.demoACC.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class DateHelper {
    private static final String DATE_PATTERN = "\\b\\d{4}-\\d{2}-\\d{2}\\b";

    public static List<String> extractDates(String text) {
        List<String> date = new ArrayList<>();
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            date.add(matcher.group());
        }

        return date;
    }


}
