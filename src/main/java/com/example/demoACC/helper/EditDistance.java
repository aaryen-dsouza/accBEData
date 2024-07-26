package com.example.demoACC.helper;

public class EditDistance {
    public static int calculate(String firstWord, String secondWord) {
        int m = firstWord.length();
        int n = secondWord.length();
        int[][] deepee = new int[m + 1][n + 1];

        for (int iteratorED = 0; iteratorED <= m; iteratorED++) {
            for (int iteratorJED = 0; iteratorJED <= n; iteratorJED++) {
                if (iteratorED == 0) {
                    deepee[iteratorED][iteratorJED] = iteratorJED;
                } else if (iteratorJED == 0) {
                    deepee[iteratorED][iteratorJED] = iteratorED;
                } else if (firstWord.charAt(iteratorED - 1) == secondWord.charAt(iteratorJED - 1)) {
                    deepee[iteratorED][iteratorJED] = deepee[iteratorED - 1][iteratorJED - 1];
                } else {
                    deepee[iteratorED][iteratorJED] = 1 + Math.min(deepee[iteratorED - 1][iteratorJED], Math.min(deepee[iteratorED][iteratorJED - 1], deepee[iteratorED - 1][iteratorJED - 1]));
                }
            }
        }
        return deepee[m][n];
    }

    public static void main(String[] args) {
        String firstWord = "";
        String secondWord = "";
        System.out.println(calculate(firstWord, secondWord));  // Output: 3
    }
}