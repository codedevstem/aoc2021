package day3;

import utils.FileReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        String[] lines = FileReader.readLinesFromFileToStringArray("./src/day3/input.txt");

        String[][] oxygenChars = Arrays.stream(lines).map(m -> m.split("")).toArray(String[][]::new);
        String[][] co2Chars = Arrays.stream(lines).map(m -> m.split("")).toArray(String[][]::new);
        int lineIndex = 0;
        while (oxygenChars.length > 1) {
            int zeroes = 0, ones = 0;
            for (String[] oxygenChar : oxygenChars) {
                if (oxygenChar[lineIndex].equals("0")) {
                    zeroes++;
                } else {
                    ones++;
                }
            }
            String bitCriteria = ones >= zeroes ? "1" : "0";
            int finalI = lineIndex;
            oxygenChars = Arrays.stream(oxygenChars).filter(x -> String.valueOf(x[finalI]).equals(bitCriteria)).toArray(String[][]::new);
            lineIndex++;
        }

        lineIndex = 0;
        while (co2Chars.length > 1) {
            int zeroes = 0, ones = 0;
            for (String[] co2Char : co2Chars) {
                if (co2Char[lineIndex].equals("0")) {
                    zeroes++;
                } else {
                    ones++;
                }
            }
            String bitCriteria = zeroes > ones ? "1" : "0";
            //Gamma
            int finalI = lineIndex;
            co2Chars = Arrays.stream(co2Chars).filter(x -> String.valueOf(x[finalI]).equals(bitCriteria)).toArray(String[][]::new);
            lineIndex++;
        }

        int oxygenRating = Integer.parseInt(String.join("", oxygenChars[0]), 2);
        int co2Rating = Integer.parseInt(String.join("", co2Chars[0]), 2);

        System.out.printf("gamma: %s, epsilon: %s --- product: %d", oxygenRating, co2Rating, oxygenRating * co2Rating);
    }

}

