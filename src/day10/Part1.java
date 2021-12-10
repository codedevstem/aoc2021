package day10;

import utils.FileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> inputArray = FileReader.readLinesFromFile("./src/day10/input.txt");
        String pattern = "(\\(]|\\(}|\\(>|\\[\\)|\\[}|\\[>|\\{\\)|\\{]|\\{>|<\\)|<}|<])";
        Pattern illegalPattern = Pattern.compile(pattern);
        Map<String, Integer> scoreMap = new HashMap<>();
        for (String s : inputArray) {
            String currentString = s;
            boolean shouldContinue = true;
            while(shouldContinue) {
                String modified = currentString.replaceAll("(\\{}|\\[]|\\(\\)|<>)", "");
                if (currentString.length() == modified.length()) {
                    shouldContinue = false;
                    Matcher illegalStringMatcher = illegalPattern.matcher(currentString);
                    if (illegalStringMatcher.find(0)) {
                        String illegalString = illegalStringMatcher.group().split("")[1];
                        if (scoreMap.get(illegalString) == null) {
                            scoreMap.put(illegalString, calculateScoreForSymbol(illegalString));
                            break;
                        }
                        scoreMap.put(illegalString, scoreMap.get(illegalString) + calculateScoreForSymbol(illegalString));
                    }
                }
                currentString = modified;
            }
        }
        System.out.println(scoreMap.values().stream().mapToInt(x -> x).sum());
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }

    static int calculateScoreForSymbol(String symbol) {
        switch (symbol) {
            case ")" -> {return 3; }
            case "]" -> {return 57; }
            case "}" -> {return 1197; }
            case ">" -> {return 25137; }
        }
        return 0;
    }
}