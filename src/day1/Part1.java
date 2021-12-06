package day1;

import utils.FileReader;

public class Part1 {
    public static void main(String[] args) {
        Integer[] inputLines = FileReader.readLinesFromFile("./src/day1/input.txt").stream().map(Integer::parseInt).toArray(Integer[]::new);
        int increases = 0;
        for (int i = 0; i < inputLines.length - 1; i++)
            if (inputLines[i] < inputLines[i+1]) increases++;
        System.out.println(increases);
    }
}
