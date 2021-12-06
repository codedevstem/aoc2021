package day6;

import utils.FileReader;

import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> inputLines = FileReader.readLinesFromFile("./src/day6/input.txt");
        long zero=0, one=0, two=0, three=0, four=0, five=0, six=0, seven=0, eight=0;
        for (String s : inputLines.get(0).split(",")) {
            switch (Integer.parseInt(s)) {
                case 0 -> zero++;
                case 1 -> one++;
                case 2 -> two++;
                case 3 -> three++;
                case 4 -> four++;
                case 5 -> five++;
                case 6 -> six++;
                case 7 -> seven++;
                case 8 -> eight++;
            }
        }
        for (int i = 0; i < 256; i++) {
            long tmpZero=zero;
            zero = one;
            one = two;
            two = three;
            three = four;
            four = five;
            five = six;
            six = tmpZero + seven;
            seven = eight;
            eight = tmpZero;
        }
        System.out.printf("fishes the school: %d", zero + one + two + three + four + five + six + seven + eight);

        System.out.printf("finished in %d ms\n", System.currentTimeMillis() - startTime);
    }
}
