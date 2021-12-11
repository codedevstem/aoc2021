package day11;

import java.util.ArrayList;
import java.util.List;

public class Common {
    static void printColonyMap(List<Octopus> colony, String step) {
        System.out.printf("step : %s", step);
        for (int i = 0; i < colony.size(); i++) {
            if (i % 10 == 0) System.out.println();
            System.out.printf("%d", colony.get(i).energy);
        }
        System.out.println();
    }

    static List<Octopus> findNeighbours(List<List<Octopus>> octopuses, int row, int col) {
        List<Octopus> neighbours = new ArrayList<>();
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (i == row && j == col) continue;
                try {
                    neighbours.add(octopuses.get(i).get(j));
                } catch (Exception ignored){}
            }
        }
        return neighbours;
    }

    static int flashes = 0;

    static class Octopus {
        int energy;
        List<Octopus> neighbours;
        boolean hasFlashed;

        public Octopus(int energy) {
            this.energy = energy;
            this.neighbours = new ArrayList<>();
            this.hasFlashed = false;
        }

        void addNeighbours(List<Octopus> neighbours) {
            this.neighbours = neighbours;
        }
        void charge() {
            this.energy++;
        }

        void flashIfEnergyTooHighAndNotFlashedBefore() {
            if (this.energy > 9) {
                if (!this.hasFlashed) {
                    flashes++;
                    this.hasFlashed = true;
                    this.neighbours.forEach(Octopus::charge);
                    this.neighbours.forEach(Octopus::flashIfEnergyTooHighAndNotFlashedBefore);
                }
            }
        }

        void discharge() {
            if (this.hasFlashed) {
                this.hasFlashed = false;
                this.energy = 0;
            }
        }
    }
}
