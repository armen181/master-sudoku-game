package armen.mastersudoku;

import armen.mastersudoku.util.SudokuForm;

public interface GameService {
    void init();

    SudokuForm[][] checkAnswer(int x, int y, int value);

    Sudoku generate(int mode);

    Sudoku getAnswer();

    SudokuForm[][] getMatrix();

    SudokuForm getPoint(int x, int y);



}
