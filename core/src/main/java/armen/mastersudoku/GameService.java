package armen.mastersudoku;

import armen.mastersudoku.util.SudokuForm;

public interface GameService {
    void init();

    SudokuForm[][] checkAnswer(int x, int y, int value);

    SudokuForm[][] generate(int mode);

    SudokuForm[][] getMatrix();

    SudokuForm getPoint(int x, int y);



}
