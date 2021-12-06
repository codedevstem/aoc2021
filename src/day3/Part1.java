package day3;

import utils.FileReader;

import java.util.HashMap;
import java.util.Map;

public class Part1 {
    public static void main(String[] args) {
        String[] lines = FileReader.readLinesFromFileToStringArray("./src/day3/input.txt");
        Map<Integer, String> map = new HashMap<>();
        for (String s : lines) {
            String[] line = s.split("");
            for (int j = 0; j < line.length; j++) {
                int mapIndex = j % line.length;
                if (map.get(mapIndex) == null) {
                    map.put(mapIndex, line[j]);
                    continue;
                }
                map.put(mapIndex, map.get(mapIndex) + line[j]);
            }
        }
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (String value : map.values()) {
            int zeroes = 0, ones = 0;
            String[] line = value.split("");
            for (String s : line) {
                if (s.equals("0")) {
                    zeroes++;
                } else {
                    ones++;
                }
            }
            if (zeroes > ones) {
                epsilon.append("1");
                gamma.append("0");
            } else {
                epsilon.append("0");
                gamma.append("1");
            }
        }
        int gammaValue = Integer.parseInt(gamma.toString(), 2);
        int epsilonValue = Integer.parseInt(epsilon.toString(), 2);

        System.out.printf("gamma: %s, epsilon: %s --- product: %d", gammaValue, epsilonValue, gammaValue * epsilonValue);
    }


}
