package day4;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    static class BingoBoard {
        BoardNumber[][] numbers;
        boolean hasWon = false;
        public BingoBoard(BoardNumber[][] numbers) {
            this.numbers = numbers;
        }

        public boolean isHasWon() {
            return hasWon;
        }

        public void setHasWon(boolean hasWon) {
            this.hasWon = hasWon;
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
        BingoBoard lastBoardToWin = null;
        int lastWinNumber = -1;
        for (Integer number : numbers) {
            for (BingoBoard board : boards) {
                if (board.isHasWon()) {
                    continue;
                }
                BoardNumber[][] boardNumbers = board.numbers;
                for (int i = 0; i < boardNumbers.length; i++) {
                    BoardNumber[] boardRows = boardNumbers[i];
                    for (int j = 0; j < boardRows.length; j++) {
                        BoardNumber boardNumber = boardRows[j];
                        if (boardNumber.value.equals(number)) {
                            boardNumber.marked = true;
                            if (checkBoardRow(board, i) || checkBoardCol(board, j)) {
                                board.setHasWon(true);
                                lastBoardToWin = board;
                                lastWinNumber = number;
                            }
                        }
                    }
                }
            }
        }
        if (lastBoardToWin == null) {
            System.out.println("fucked up");
            System.exit(-1);
        }
        int sumUnmarkedNumbersOnBoard = sumUnmarkedNumbersOnBoard(lastBoardToWin);
        System.out.printf("Last board to win: unmarkedNumbers: %d, currentDrawnNumber: %d, finalScore: %d\n", sumUnmarkedNumbersOnBoard, lastWinNumber, sumUnmarkedNumbersOnBoard*lastWinNumber);
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
