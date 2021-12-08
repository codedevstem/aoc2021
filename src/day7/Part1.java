package day7;

import utils.FileReader;

import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Integer[] input = Arrays.stream(FileReader.readLinesFromFile("./src/day7/input.txt").get(0).split(",")).map(Integer::parseInt).sorted().toArray(Integer[]::new);
        int median = input[input.length / 2];
        int fuelSpent = 0;
        for (Integer crabPosition : input) {
            switch (crabPosition.compareTo(median)){
                case -1 -> fuelSpent += median - crabPosition;
                case 0 -> fuelSpent += 0;
                case 1 -> fuelSpent += crabPosition - median;
            }
        }
        System.out.println(fuelSpent);
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }
}
