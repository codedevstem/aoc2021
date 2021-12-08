package day8;

import utils.FileReader;

import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int result = FileReader.readLinesFromFile("./src/day8/input.txt").stream().map(x -> x.split("\\|")[1].trim().split(" ")).map(x ->
                Arrays.stream(x).filter(y -> y.length() == 2 || y.length() == 3 || y.length() == 4 || y.length() == 7).toList().size()).mapToInt(x -> x).sum();
        System.out.println(result);
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);

    }
}

