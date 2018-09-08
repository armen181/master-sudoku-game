package armen.mastersudoku;

import armen.mastersudoku.util.SudokuForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    Random random = new Random();
    private SudokuForm[][] sudokuForms = new SudokuForm[9][9];
    private Sudoku sudokuAll = new Sudoku();
    private boolean state = false;
    private boolean readyForPlay = false;

    @Override
    public void initOrReset() {
        readyForPlay = false;
        state = false;

    }

    @Override
    public boolean checkAnswer(Sudoku sudoku) {
        return false;
    }

    @Override
    public SudokuForm[][] setAnswer(int x, int y, int value) {
        sudokuForms[x][y]= new SudokuForm(value,false, true);
        sudokuForms =chackTable(sudokuForms);
        return sudokuForms;

    }

    @Override
    public Sudoku generate() {
        if (!state) {
            sudokuAll = generateRandomSudoku();
            state = true;
        }

        return sudokuAll;
    }

    @Override
    public Sudoku getAnswer() {
        return sudokuAll;
    }


    // modes - 1: easy, 2: medium, 3 hard.
    @Override
    public SudokuForm[][] getPlayableMatrix(int mode) {
        if (!readyForPlay) {
            int count = 0;
            switch (mode) {
                case 1:
                    count = 4;
                    break;
                case 2:
                    count = 3;
                    break;
                case 3:
                    count = 2;
                    break;
            }
            for (int i = 0; i < 9; i++) {
                int x = 0;
                int y = 0;

                switch (i) {
                    case 0:
                        x = 0;
                        y = 0;
                        break;
                    case 1:
                        x = 3;
                        y = 0;
                        break;
                    case 2:
                        x = 6;
                        y = 0;
                        break;
                    case 3:
                        x = 0;
                        y = 3;
                        break;
                    case 4:
                        x = 3;
                        y = 3;
                        break;
                    case 5:
                        x = 6;
                        y = 3;
                        break;
                    case 6:
                        x = 0;
                        y = 6;
                        break;
                    case 7:
                        x = 3;
                        y = 6;
                        break;
                    case 8:
                        x = 6;
                        y = 6;
                        break;
                }


                List<Integer> randomNumbers = new ArrayList<>();
                for (int j = 0; j < count; j++) {

                    for (int l = 0; l < 200; l++) {
                        int randomValue = random.nextInt(8);
                        boolean match = false;
                        for (int k : randomNumbers) {
                            if (k == randomValue)
                                match = true;
                        }
                        if (!match) {
                            randomNumbers.add(randomValue);
                            break;
                        }
                    }
                }


                for (int f = 0; f < 9; f++) {
                    boolean match = false;
                    for (int n : randomNumbers) {
                        if (n == f){
                            match = true;
                            sudokuForms[x + (f % 3)][y + (f / 3)] = new SudokuForm(sudokuAll.getMatrix()[x + (f % 3)][y + (f / 3)], false, false);
                        }
                    }
                    if (!match) {

                        sudokuForms[x + (f % 3)][y + (f / 3)] = new SudokuForm(0, false , true);
                    }
                }


            }

            readyForPlay=true;
        }


        return sudokuForms;
    }

    @Override
    public SudokuForm getPoint(int x, int y) {
        return null;
    }

    private static void shuffleArray(Integer[] array) {
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
                int c = i % 3 + columnOrder;
                int k = (i + columnOrder) % 9 + 1;
                k = shuffledArray[k - 1];
                if (row > 0) {
                    int roeUpper = r
                            - 3 * row;
                    int colUpper = (c + row)
                            % 9;
                    k = matrix[roeUpper][colUpper];

                }
                matrix[r][c] = k;

            }
        }

    }


    public static Sudoku generateRandomSudoku() {

        Integer[] array = Sudoku.SUDOKU_NUMBERS.toArray(new Integer[]{});
        shuffleArray(array);
        Sudoku sudoku = new Sudoku();
        for (int row = 0; row < 3; row++) {
            populateRow(sudoku.getMatrix(), row, array);
        }
        return sudoku;
    }

    private SudokuForm[][] chackTable(SudokuForm[][] sudokuForm){

        for(int i=0; i<9; i++){
            for(int j=0;j<9;j++){
                sudokuForm[i][j].setCorrect(false);
            }
        }

        for(int i=0; i<9; i++ ){
            List<SudokuForm> list = new ArrayList<>();
            for(int j=0; j<9; j++){
                for(int k=0 ; k<list.size(); k++){

                    if(list.get(k).getValue()==sudokuForm[i][j].getValue() && list.get(k).getValue()!=0 ){
                        sudokuForm[i][k].setCorrect(true);
                        sudokuForm[i][j].setCorrect(true);

                    }

                }
                list.add(sudokuForm[i][j]);
            }

        }
        for(int j=0; j<9; j++ ){
                List<SudokuForm> list = new ArrayList<>();
                for(int i=0; i<9; i++){
                    for(int k=0 ; k<list.size(); k++){

                        if(list.get(k).getValue()==sudokuForm[i][j].getValue() && list.get(k).getValue()!=0 ){
                            sudokuForm[k][j].setCorrect(true);
                            sudokuForm[i][j].setCorrect(true);

                        }

                    }
                    list.add(sudokuForm[i][j]);
                }

        }

        for(int i=0; i<9; i++ ){
            List<SudokuForm> list = new ArrayList<>();
            int x = 0;
            int y = 0;
            switch (i) {
                case 0:
                    x = 0;
                    y = 0;
                    break;
                case 1:
                    x = 3;
                    y = 0;
                    break;
                case 2:
                    x = 6;
                    y = 0;
                    break;
                case 3:
                    x = 0;
                    y = 3;
                    break;
                case 4:
                    x = 3;
                    y = 3;
                    break;
                case 5:
                    x = 6;
                    y = 3;
                    break;
                case 6:
                    x = 0;
                    y = 6;
                    break;
                case 7:
                    x = 3;
                    y = 6;
                    break;
                case 8:
                    x = 6;
                    y = 6;
                    break;
            }
            for(int j=0; j<9; j++){

                for(int k=0 ; k<list.size(); k++){

                    if(list.get(k).getValue()==sudokuForm[x + (j % 3)][y + (j / 3)].getValue() && list.get(k).getValue()!=0 ){
                        sudokuForms[x + (j % 3)][y + (j / 3)].setCorrect(true);
                        sudokuForms[x + (k % 3)][y + (k / 3)].setCorrect(true);

                    }

                }
                list.add(sudokuForm[x + (j % 3)][y + (j / 3)]);
            }

        }



         return sudokuForm;
    }


}
