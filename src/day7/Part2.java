package day7;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[] input = Arrays.stream(FileReader.readLinesFromFile("./src/day7/test.txt").get(0).split(",")).map(Integer::parseInt).mapToInt(i -> i).sorted().toArray();
        int[] medians = new int[2];
        medians[0] = (int) Math.floor((float) IntStream.of(input).sum() / input.length);
        medians[1] = (int) Math.ceil((float) IntStream.of(input).sum() / input.length);
        List<Integer> results = new ArrayList<>();
        for (int median : medians) {
            int fuelSpent = 0;
            for (Integer crabPosition : input) {
                switch (crabPosition.compareTo(median)){
                    case -1 -> fuelSpent += IntStream.range(0, median - crabPosition + 1).sum();
                    case 0 -> fuelSpent += 0;
                    case 1 -> fuelSpent += IntStream.range(0, crabPosition - median + 1).sum();
                }
            }
            results.add(fuelSpent);
        }
        System.out.println(results.stream().sorted().toList().get(0));
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }
}
