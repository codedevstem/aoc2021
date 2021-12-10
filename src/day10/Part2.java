package day10;

import utils.FileReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> inputArray = FileReader.readLinesFromFile("./src/day10/input.txt");
        String pattern = "(\\(]|\\(}|\\(>|\\[\\)|\\[}|\\[>|\\{\\)|\\{]|\\{>|<\\)|<}|<])";
        Pattern illegalPattern = Pattern.compile(pattern);
        List<Long> scoreList = new ArrayList<>();
        for (String s : inputArray) {
            String currentString = s;
            boolean shouldContinue = true;
            while(shouldContinue) {
                String modified = currentString.replaceAll("(\\{}|\\[]|\\(\\)|<>)", "");
                if (currentString.length() == modified.length()) {
                    shouldContinue = false;
                    Matcher illegalStringMatcher = illegalPattern.matcher(currentString);
                    if (illegalStringMatcher.find()) {
                        break;
                    }
                    currentString = currentString.replaceAll("\\(", ")");
                    currentString = currentString.replaceAll("\\[", "]");
                    currentString = currentString.replaceAll("\\{", "}");
                    currentString = currentString.replaceAll("<", ">");
                    StringBuilder sb = new StringBuilder(currentString);
                    scoreList.add(calculateScore(sb.reverse().toString()));
                }
                currentString = modified;
            }
        }
        Collections.sort(scoreList);
        System.out.println(scoreList.get(scoreList.size()/2));
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }

    private static long calculateScore(String scoreString) {
        long totalScore = 0;
        for (String s : scoreString.split("")) {
            totalScore *= 5;
            switch (s) {
                case ")" -> totalScore += 1;
                case "]" -> totalScore += 2;
                case "}" -> totalScore += 3;
                case ">" -> totalScore += 4;
            }
        }
        return totalScore;
    }
}
