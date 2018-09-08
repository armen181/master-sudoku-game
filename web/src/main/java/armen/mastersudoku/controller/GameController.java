package armen.mastersudoku.controller;


import armen.mastersudoku.GameService;
import armen.mastersudoku.util.SudokuForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
public class GameController {


    // == field ==


    private GameService gameService;


    private boolean status = false;
    private boolean use50_50 = false;


    // == constructor ==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    // === RQ ===
    @PostMapping("startGame")
    public SudokuForm[][] getQuestion(@RequestHeader int mode) {
        gameService.generate();
        return gameService.getPlayableMatrix(mode);
    }

    @PostMapping("setValue")
    public SudokuForm[][] getQuestion(@RequestHeader int x, @RequestHeader int y, @RequestHeader int value) {
        return gameService.setAnswer(x, y, value);
    }

    @PostMapping("reset")
    public SudokuForm[][] initOrReset(@RequestHeader int mode) {
        gameService.initOrReset();
        gameService.generate();
        return gameService.getPlayableMatrix(mode);
    }

    @GetMapping("end")
    public SudokuForm[][] end() {
        gameService.initOrReset();
        SudokuForm sudokuForm = new SudokuForm(0,false,true);
        SudokuForm[][]  array = new SudokuForm[][] {{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm},{sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm,sudokuForm}};
        return array;
    }


}
