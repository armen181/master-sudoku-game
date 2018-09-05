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
//    private static final int NUM_CELLS = Sudoku.MAX_VALID_SUDOKU_NUMBER
//            * Sudoku.MAX_VALID_SUDOKU_NUMBER;
    private Sudoku sudokuAll = new Sudoku();
    private boolean state = false;

    @Override
    public void init() {

    }

    @Override
    public SudokuForm[][] checkAnswer(int x, int y, int value) {
        return new SudokuForm[0][];
    }

    @Override
    public Sudoku generate(int mode) {
        if(!state) {
            sudokuAll = generateRandomSudoku();
            state=true;
        }

       return sudokuAll;
    }

    @Override
    public Sudoku getAnswer() {
        return sudokuAll;
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
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                int r = i / 3
                        + (3 * row);
                int columnOrder = 3 * j;
                int c = i % 3+ columnOrder;
                int k = (i + columnOrder) % 9 + 1;
                k = shuffledArray[k - 1];
                if (row > 0) {
                    int _rowUpperBandToBeCopied = r
                            - 3* row;
                    int _columnUpperBandToBeCopied = (c + row)
                            % 9;
                    k = matrix[_rowUpperBandToBeCopied][_columnUpperBandToBeCopied];

                }
                matrix[r][c] = k;

            }
        }

    }



    public static Sudoku generateRandomSudoku() {

        Integer[] array = Sudoku.SUDOKU_NUMBERS.toArray(new Integer[] {});
        _shuffleArray(array);
        Sudoku _sudoku = new Sudoku();
        for (int row = 0; row < 3; row++) {
            populateRow(_sudoku.getMatrix(), row, array);
        }
        return _sudoku;
    }

    public static Sudoku cloneSudoku(Sudoku su){
        return su ;
    }

}
