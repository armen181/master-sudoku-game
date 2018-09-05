package armen.mastersudoku;

import armen.mastersudoku.util.SudokuForm;

import java.util.List;

public class GameServiceImpl implements GameService {

   private SudokuForm sudokuForm = new SudokuForm(0,false);


    @Override
    public void init() {

    }

    @Override
    public SudokuForm[][] checkAnswer(int x, int y, int value) {
        return new SudokuForm[0][];
    }

    @Override
    public SudokuForm[][] generate(int mode) {


        return new SudokuForm[0][];
    }

    @Override
    public SudokuForm[][] getMatrix() {
        return new SudokuForm[0][];
    }

    @Override
    public SudokuForm getPoint(int x, int y) {
        return null;
    }
}
