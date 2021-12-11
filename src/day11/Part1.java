package day11;

import utils.FileReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static day11.Common.*;

public class Part1 {
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
        for (int i = 0; i < 100; i++) {
            colony.forEach(Common.Octopus::charge);
            colony.forEach(Common.Octopus::flashIfEnergyTooHighAndNotFlashedBefore);
            colony.forEach(Common.Octopus::discharge);
            printColonyMap(colony, String.valueOf(i));
        }
        System.out.println(flashes);
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }
}