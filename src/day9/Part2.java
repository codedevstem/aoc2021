package day9;

import utils.FileReader;

import java.util.*;

public class Part2 {
    static class Point {
        int x;
        int y;
        int value;
        boolean visited;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Integer[][] inputArray = FileReader.readLinesFromFile("./src/day9/input.txt").stream().map(x -> Arrays.stream(x.split("")).map(Integer::parseInt).toArray(Integer[]::new)).toArray(Integer[][]::new);
        List<Point> lowPoints = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i][j-1] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i][j-1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i-1][j] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j-1]) lowPoints.add(new Point(i, j)); continue;} catch (Exception ignored){}
                try {if (inputArray[i][j] < inputArray[i+1][j] && inputArray[i][j] < inputArray[i][j+1]) lowPoints.add(new Point(i, j));} catch (Exception ignored){}
            }
        }
        Point[][] pointMap = new Point[inputArray.length][inputArray[0].length];
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                pointMap[i][j] = new Point(i, j, inputArray[i][j]);
            }
        }
        List<Integer> basinSizes = new ArrayList<>();
        for (Point lowPoint : lowPoints) {
            basinSizes.add(calculateSizeOfLowpointBasin(pointMap, lowPoint));
        }
        basinSizes.sort(Integer::compareTo);
        Collections.reverse(basinSizes);
        int basinProduct = 1;
        for (int i = 0; i < 3; i++) {
            basinProduct *= basinSizes.get(i);
        }

        System.out.println(basinProduct);
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }

    private static int calculateSizeOfLowpointBasin(Point[][] pointMap, Point lowPoint) {
        int x = lowPoint.x;
        int y = lowPoint.y;
        pointMap[x][y].visited = true;
        int size = 1;
        try {if(!pointMap[x-1][y].visited && pointMap[x-1][y].value != 9) size += calculateSizeOfLowpointBasin(pointMap, pointMap[x-1][y]);}catch (Exception ignored){}
        try {if(!pointMap[x+1][y].visited && pointMap[x+1][y].value != 9) size += calculateSizeOfLowpointBasin(pointMap, pointMap[x+1][y]);}catch (Exception ignored){}
        try {if(!pointMap[x][y-1].visited && pointMap[x][y-1].value != 9) size += calculateSizeOfLowpointBasin(pointMap, pointMap[x][y-1]);}catch (Exception ignored){}
        try {if(!pointMap[x][y+1].visited && pointMap[x][y+1].value != 9) size += calculateSizeOfLowpointBasin(pointMap, pointMap[x][y+1]);}catch (Exception ignored){}
        return size;
    }
}
