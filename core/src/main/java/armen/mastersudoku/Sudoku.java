package armen.mastersudoku;

import java.util.HashSet;
import java.util.Set;

public class Sudoku{

    public static final Set<Integer> SUDOKU_NUMBERS = new HashSet<>(9);


    static {
        for (int i = 1; i <= 9; i++) {
            SUDOKU_NUMBERS.add(i);

        }
    }

    private Integer[][] matrix = new Integer[9][9];


    public Integer[][] getMatrix() {
        return matrix;
    }


}