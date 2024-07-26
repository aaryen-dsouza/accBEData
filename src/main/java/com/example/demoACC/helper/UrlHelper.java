package com.example.demoACC.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlHelper {
    private static final String URL_PATTERN = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public static List<String> extractUrls(String text) {
        List<String> urls = new ArrayList<>();
        Pattern urlPattern = Pattern.compile(URL_PATTERN);
        Matcher urlMatcher = urlPattern.matcher(text);

        while (urlMatcher.find()) {
            urls.add(urlMatcher.group());
        }

        return urls;
    }
}
