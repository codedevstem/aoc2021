package day5;

import java.util.*;

public class Part2 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Common.Line> lineList = Common.ParseInput();
        Map<Common.Point, Integer> map = new HashMap<>();

        for (Common.Line line : lineList) {
            if (line == null) continue;
            int x = line.startX, y = line.startY;
            while (x != line.endX || y != line.endY) {
                Common.Point current = new Common.Point(x, y);
                if (map.get(current) == null) {
                    map.put(current, 0);
                } else {
                    map.put(current, map.get(current) + 1);
                }
                if (line.startX < line.endX) x++;
                if (line.startX > line.endX) x--;
                if (line.startY < line.endY) y++;
                if (line.startY > line.endY) y--;

            }
            Common.Point current = new Common.Point(x, y);
            if (map.get(current) == null) {
                map.put(current, 0);
            } else {
                map.put(current, map.get(current) + 1);
            }

        }
        int moreThanTwo = (int) map.values().stream().filter(x -> x > 0).count();
        System.out.println(moreThanTwo);
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }
}
