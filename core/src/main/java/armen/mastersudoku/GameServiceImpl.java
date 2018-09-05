package armen.mastersudoku;

import armen.mastersudoku.util.SudokuForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

   private SudokuForm sudokuForm = new SudokuForm(0,false);
   Random random = new Random();
    private static final int NUM_CELLS = Sudoku.MAX_VALID_SUDOKU_NUMBER
            * Sudoku.MAX_VALID_SUDOKU_NUMBER;


    @Override
    public void init() {

    }

    @Override
    public SudokuForm[][] checkAnswer(int x, int y, int value) {
        return new SudokuForm[0][];
    }

    @Override
    public Sudoku generate(int mode) {



        return generatePlayableRandomSudoku(Sudoku.SudokuLevel.MEDIUM);
    }

    @Override
    public SudokuForm[][] getMatrix() {
        return new SudokuForm[0][];
    }

    @Override
    public SudokuForm getPoint(int x, int y) {
        return null;
    }

    private static void _shuffleArray(Integer[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private static void populateRow(Integer[][] matrix, int row,
                                    Integer shuffledArray[]) {
        // Iterator over 3 as the number of 3*3 cells for each row
        // int tre = 3;
        for (int j = 0; j < Sudoku.SIZE_OF_EACH_SQUARE; j++) {
            // populate each 3*3 cell
            for (int i = 0; i < Sudoku.MAX_VALID_SUDOKU_NUMBER; i++) {
                int r = i / Sudoku.SIZE_OF_EACH_SQUARE
                        + (Sudoku.SIZE_OF_EACH_SQUARE * row);
                int columnOrder = Sudoku.SIZE_OF_EACH_SQUARE * j;
                int c = i % Sudoku.SIZE_OF_EACH_SQUARE + columnOrder;
                int k = (i + columnOrder) % Sudoku.MAX_VALID_SUDOKU_NUMBER + 1;
                k = shuffledArray[k - 1];
                // Once the first row is populated the other
                // the others can be populated via Band permutations
                if (row > 0) {
                    int _rowUpperBandToBeCopied = r
                            - Sudoku.SIZE_OF_EACH_SQUARE * row;
                    int _columnUpperBandToBeCopied = (c + row)
                            % Sudoku.MAX_VALID_SUDOKU_NUMBER;
                    k = matrix[_rowUpperBandToBeCopied][_columnUpperBandToBeCopied];

                }
                matrix[r][c] = k;

            }
        }

    }



    public static Sudoku generatePlayableRandomSudoku(Sudoku.SudokuLevel level) {
        Sudoku s = generateRandomSudoku();
        Integer n[] = new Integer[NUM_CELLS];
        for (int i = 0; i < NUM_CELLS; i++) {
            n[i] = i;
        }
        _shuffleArray(n);
        for (int i = 0; i < NUM_CELLS-level.getValorizedCells(); i++) {
            int row_cols = n[i];
            int r = row_cols/9;
            int c = row_cols%9;
            s.getMatrix()[r][c] =0;
        }
        return s;

    }

    public static Sudoku generateRandomSudoku() {

        Integer[] array = Sudoku.SUDOKU_NUMBERS.toArray(new Integer[] {});
        _shuffleArray(array);
        Sudoku _sudoku = new Sudoku();
        for (int row = 0; row < Sudoku.SIZE_OF_EACH_SQUARE; row++) {
            populateRow(_sudoku.getMatrix(), row, array);
        }
        return _sudoku;
    }

}
