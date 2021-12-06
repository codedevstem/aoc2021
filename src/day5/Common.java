package day5;

import utils.FileReader;

import java.util.List;
import java.util.Objects;

public class Common {
    static class Line {
        int startX;
        int startY;
        int endX;
        int endY;

        public Line(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
    static class Point implements Comparable {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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

        @Override
        public int compareTo(Object o) {
            Point point = (Point) o;
            if (x < point.x) return -1;
            else if (x > point.x) return 1;
            else {
                return Integer.compare(y, point.y);
            }
        }

        @Override
        public String toString() {
            return String.format("%d, %d", this.x, this.y);
        }
    }
    static List<Line> ParseInput() {
        List<String> lines = FileReader.readLinesFromFile("./src/day5/input.txt");
        List<Common.Line> lineList = lines.stream().map(x -> {
            String[] lineParts = x.split(" ");
            String[] startCords = lineParts[0].split(",");
            String[] endCords = lineParts[2].split(",");
            int x1 = Integer.parseInt(startCords[0]), y1 = Integer.parseInt(startCords[1]), x2 = Integer.parseInt(endCords[0]), y2 = Integer.parseInt(endCords[1]);
            return new Common.Line(x1, y1, x2, y2);
        }).toList();
        return lineList;
    }
}
