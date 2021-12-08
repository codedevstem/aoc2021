package day8;

import utils.FileReader;

import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<List<String[]>> parsedInput = FileReader.readLinesFromFile("./src/day8/input.txt").stream().map(x ->
                Arrays.stream(x.split("\s\\|\s")).map(y ->
                        Arrays.stream(y.split(" ")).toArray(String[]::new)).toList()).toList();
        int total = 0;
        for (List<String[]> inputLines : parsedInput) {
            String[] allkeys = Arrays.stream(inputLines.get(0)).sorted(Comparator.comparingInt(String::length)).toArray(String[]::new);
            Map<String, String> keyMap = new HashMap<>();
            keyMap.put("1", Arrays.stream(allkeys).filter(x -> x.length() == 2).findFirst().orElse(""));
            keyMap.put("7", Arrays.stream(allkeys).filter(x -> x.length() == 3).findFirst().orElse(""));
            keyMap.put("4", Arrays.stream(allkeys).filter(x -> x.length() == 4).findFirst().orElse(""));
            keyMap.put("8", Arrays.stream(allkeys).filter(x -> x.length() == 7).findFirst().orElse(""));
            List<String> length5Keys = Arrays.stream(allkeys).filter(x -> x.length() == 5).toList();
            List<String> length6Keys = Arrays.stream(allkeys).filter(x -> x.length() == 6).toList();
            for (String length5Key : length5Keys) {
                if (length5Key.contains(keyMap.get("1").split("")[0]) && length5Key.contains(keyMap.get("1").split("")[1])) keyMap.put("3", length5Key);
            }
            String inFourButNotInOne = lettersNotInWord(keyMap.get("1"), keyMap.get("4"));
            for (String length5Key : length5Keys) {
                if(length5Key.equals(keyMap.get("3"))) continue;
                String lettersNotInKey = lettersNotInWord(length5Key, inFourButNotInOne);
                if(lettersNotInKey.length() == 0) keyMap.put("5", length5Key);
                else keyMap.put("2", length5Key);
            }
            for (String length6Key : length6Keys) {
                if(lettersNotInWord(keyMap.get("1"), length6Key).length() == 5) keyMap.put("6", length6Key);
            }
            for (String length6Key : length6Keys) {
                if(length6Key.equals(keyMap.get("6"))) continue;
                if(lettersNotInWord(keyMap.get("4"), length6Key).length() == 2) keyMap.put("9", length6Key);
                else keyMap.put("0", length6Key);
            }
            StringBuilder valueString = new StringBuilder();
            for (String s : inputLines.get(1)) {
                String sortedString = Arrays.stream(s.split("")).sorted().reduce((x1,x2) -> x1+x2).orElse("");
                for (String key : keyMap.keySet()) {
                    String sortedValue = Arrays.stream(keyMap.get(key).split("")).sorted().reduce((x1,x2) -> x1+x2).orElse("");
                    if (sortedString.equals(sortedValue)) {
                        valueString.append(key);
                        break;
                    }
                }
            }
            total += Integer.parseInt(valueString.toString());
        }
        System.out.println(total);
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }

    private static String lettersNotInWord(String word, String filter) {
        StringBuilder lettersNotInWord = new StringBuilder();
        for (String s : filter.split("")) {
            if (!word.contains(s)) {
                lettersNotInWord.append(s);
            }
        }
        return lettersNotInWord.toString();
    }
}

