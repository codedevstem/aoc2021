package day9;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Integer[][] inputArray = FileReader.readLinesFromFile("./src/day9/input.txt").stream().map(x -> Arrays.stream(x.split("")).map(Integer::parseInt).toArray(Integer[]::new)).toArray(Integer[][]::new);
        List<Integer> lowPoints = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i][j-1] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i][j-1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1]) lowPoints.add(inputArray[i][j]+1); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(inputArray[i][j]+1);} catch (Exception ignored){}
            }
        }
        System.out.println(lowPoints.stream().mapToInt(x -> x).sum());
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }
}
