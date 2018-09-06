package armen.mastersudoku;

import armen.mastersudoku.util.SudokuForm;

public interface GameService {
    void initOrReset();

    boolean checkAnswer(Sudoku sudoku);

    SudokuForm[][] setAnswer(int x, int y, int value);

    Sudoku generate();

    Sudoku getAnswer();

    SudokuForm[][] getPlayableMatrix(int mode);

    SudokuForm getPoint(int x, int y);





}
