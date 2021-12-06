package day2;

public class Common {
    public static class Instruction {
        Direction direction;
        int vector;

        public Instruction(Direction direction, int vector) {
            this.direction = direction;
            this.vector = vector;
        }
    }

    public enum Direction {
        forward,
        down,
        up,
    }

}
