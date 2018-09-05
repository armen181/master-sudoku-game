package armen.mastersudoku;

import armen.mastersudoku.util.SudokuViewUtils;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {

    public static final String SEPARATOR = ",";

    public static final int MIN_VALID_SUDOKU_NUMBER = 1;
    public static final int MAX_VALID_SUDOKU_NUMBER = 9;

    public static final int SIZE_OF_EACH_SQUARE = 3;
    public static final int VALUE_FOR_EMPTY_CELL = 0;

    public static final Set<Integer> SUDOKU_NUMBERS = new HashSet<Integer>(
            MAX_VALID_SUDOKU_NUMBER);

    public enum SudokuLevel {
        EASY(32), MEDIUM(24), DIFFICULT(18);

        private int valorizedCells;

        private SudokuLevel(int valorizedCells) {
            this.valorizedCells = valorizedCells;
        }

        public int getValorizedCells() {
            return valorizedCells;
        }

    }

    static {
        for (int i = MIN_VALID_SUDOKU_NUMBER; i <= MAX_VALID_SUDOKU_NUMBER; i++) {
            SUDOKU_NUMBERS.add(i);

        }
    }

    private Integer[][] matrix = new Integer[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];


    private boolean[][] numbersSetPerRow = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];
    private boolean[][] numbersSetPerColumns = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];

    public static boolean isValidSudokuNumber(int number) {
        return (number >= MIN_VALID_SUDOKU_NUMBER - 1 && number <= MAX_VALID_SUDOKU_NUMBER);

    }

    public boolean addNumberToSudokuMatrix(int row, int col, int n) {



        if (n > 0
                && (numbersSetPerRow[row][n - 1] || numbersSetPerColumns[col][n - 1])) {
            return false;
        } else {
            matrix[row][col] = n;
            if (n > 0) {
                numbersSetPerRow[row][n - 1] = true;
                numbersSetPerColumns[col][n - 1] = true;
            }
            return true;
        }
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Integer[][] matrix) {
        this.matrix = matrix;
    }

    public boolean isValid() {
        // reinitialize the check matrix in case they has been already used to
        // stream the number in the sudoku
        numbersSetPerRow = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];
        numbersSetPerColumns = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];

        for (int row = 0; row < MAX_VALID_SUDOKU_NUMBER; row++) {
            for (int col = 0; col < MAX_VALID_SUDOKU_NUMBER; col++) {
                int n = matrix[row][col];
                if (n > VALUE_FOR_EMPTY_CELL
                        && (numbersSetPerRow[row][n - 1] || numbersSetPerColumns[col][n - 1])) {
                    return false;
                } else {
                    if (n > VALUE_FOR_EMPTY_CELL) {
                        numbersSetPerRow[row][n - 1] = true;
                        numbersSetPerColumns[col][n - 1] = true;
                    }
                }
            }
        }

        return true;

    }

   @Override
    public String toString() {
        return SudokuViewUtils.printToASCII(this);
   }

}