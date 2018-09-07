package armen.mastersudoku.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SudokuForm {

    private int value;
    private boolean correct;
    private boolean change;

    public SudokuForm(int value, boolean correct, boolean change) {
        this.value = value;
        this.correct = correct;
        this.change = change;
    }
}
