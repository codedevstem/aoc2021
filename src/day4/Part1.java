package day4;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    static class BingoBoard {
        BoardNumber[][] numbers;

        public BingoBoard(BoardNumber[][] numbers) {
            this.numbers = numbers;
        }
    }
    static class BoardNumber {
        public BoardNumber(Integer value) {
            this.value = value;
            this.marked = false;
        }

        Integer value;
        boolean marked;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> lines = FileReader.readLinesFromFile("./src/day4/input.txt");
        List<Integer> numbers = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toList();
        List<BingoBoard> boards = new ArrayList<>();
        BoardNumber[][] currentBoardNumbers = new BoardNumber[5][5];
        int currentBoardRow = 0;
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            currentBoardNumbers[currentBoardRow] = Arrays.stream(line.strip().split("\s+")).map(Integer::parseInt).map(BoardNumber::new).toArray(BoardNumber[]::new);
            currentBoardRow++;
            if (i+1 == lines.size() || lines.get(i+1).equals("")) {
                boards.add(new BingoBoard(currentBoardNumbers));
                currentBoardNumbers = new BoardNumber[5][5];
                currentBoardRow = 0;
                i++;
            }
        }
        for (Integer number : numbers) {
            for (BingoBoard board : boards) {
                BoardNumber[][] boardNumbers = board.numbers;
                for (int i = 0; i < boardNumbers.length; i++) {
                    BoardNumber[] boardRows = boardNumbers[i];
                    for (int j = 0; j < boardRows.length; j++) {
                        BoardNumber boardNumber = boardRows[j];
                        if (boardNumber.value.equals(number)) {
                            boardNumber.marked = true;
                            if (checkBoardRow(board, i) || checkBoardCol(board, j)) {
                                int sumUnmarkedNumbersOnBoard = sumUnmarkedNumbersOnBoard(board);
                                System.out.printf("unmarkedNumbers: %d, currentDrawnNumber: %d, finalScore: %d\n", sumUnmarkedNumbersOnBoard, number, sumUnmarkedNumbersOnBoard*number);
                                System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
                                System.exit(-1);
                            }
                        }
                    }
                }
            }
        }
        System.out.printf("finished in %d ms", System.currentTimeMillis() - startTime);
    }

    private static boolean checkBoardRow(BingoBoard board, int row) {
        for (int i = 0; i < board.numbers.length; i++) {
            if (!board.numbers[row][i].marked) return false;
        }
        return true;
    }

    private static boolean checkBoardCol(BingoBoard board, int col) {
        for (int i = 0; i < board.numbers.length; i++) {
            if (!board.numbers[i][col].marked) return false;
        }
        return true;
    }
    private static int sumUnmarkedNumbersOnBoard(BingoBoard board) {
        int sum = 0;
        BoardNumber[][] numbers = board.numbers;
        for (int i = 0; i < numbers.length; i++) {
            BoardNumber[] row = numbers[i];
            for (int j = 0; j < row.length; j++) {
                BoardNumber number = row[j];
                if (!number.marked) {
                    sum += number.value;
                }
            }
        }
        return sum;
    }
}
