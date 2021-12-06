package day2;

import utils.FileReader;

import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        List<String> lines = FileReader.readLinesFromFile("./src/day2/input.txt");
        int dept = 0;
        int aim = 0;
        int horizontal = 0;
        Common.Instruction instruction;
        for (String line : lines) {
            String[] instructionParts = line.split(" ");
            instruction = new Common.Instruction(Common.Direction.valueOf(instructionParts[0]), Integer.parseInt(instructionParts[1]));
            switch (instruction.direction) {
                case up -> aim -= instruction.vector;
                case down -> aim += instruction.vector;
                case forward -> {
                    horizontal += instruction.vector;
                    dept = dept + (aim * instruction.vector);
                }
            }
        }
        System.out.printf("Product of dept and horizontal position: %d", dept*horizontal);
    }
}
