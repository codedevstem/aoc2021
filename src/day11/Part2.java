package day11;

import utils.FileReader;

import java.util.*;

import static day11.Common.*;

public class Part2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<List<Common.Octopus>> octopusesInput = FileReader.readLinesFromFile("./src/day11/input.txt").stream().map(x -> Arrays.stream(x.split("")).map(Integer::parseInt).map(Common.Octopus::new).toList()).toList();
        List<Common.Octopus> colony = new ArrayList<>();
        for (int i = 0; i < octopusesInput.size(); i++) {
            for (int j = 0; j < octopusesInput.get(i).size(); j++) {
                octopusesInput.get(i).get(j).addNeighbours(findNeighbours(octopusesInput, i, j));
                colony.add(octopusesInput.get(i).get(j));
            }
        }
        printColonyMap(colony, "Init");
        int lastFlashCount = 0;
        for (int i = 1; i <= 1000; i++) {
            colony.forEach(Common.Octopus::charge);
            colony.forEach(Common.Octopus::flashIfEnergyTooHighAndNotFlashedBefore);
            colony.forEach(Common.Octopus::discharge);
            printColonyMap(colony, String.valueOf(i));
            if (lastFlashCount == flashes - 100) {
                System.out.println(i);
                System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
                System.exit(1);
            }
            lastFlashCount = flashes;
        }
        System.out.println("failed to find synchronous state... you fail");
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }


}