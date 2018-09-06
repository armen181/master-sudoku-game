package armen.mastersudoku.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SudokuForm {

    private int value;
    private boolean correct;

    public SudokuForm(int value, boolean correct) {
        this.value = value;
        this.correct = correct;
    }


}
