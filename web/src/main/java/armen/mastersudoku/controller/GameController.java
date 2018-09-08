package armen.mastersudoku.controller;




import armen.mastersudoku.GameService;
import armen.mastersudoku.util.SudokuForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
    public SudokuForm[][] getQuestion(@RequestHeader int mode){
      gameService.generate();
     for(int i=0; i<9; i++){
         for(int j=0;j<9;j++){
             System.out.printf(" " + gameService.getPlayableMatrix(mode)[i][j].getValue() + " ");

         }
         System.out.println(" ");

     }

      return gameService.getPlayableMatrix(mode);
    }

  @PostMapping("setValue")
  public SudokuForm[][] getQuestion(@RequestHeader int x,@RequestHeader int y,@RequestHeader int value){



        gameService.setAnswer(x,y,value);
      System.out.println("x = "+ x +" y = " + y + " value = " + value);
        for(int i=0; i<9; i++){
          for(int j=0;j<9;j++){
              System.out.printf(" " + gameService.getPlayableMatrix(1)[i][j].getValue() + " "
              + gameService.getPlayableMatrix(2)[i][j].isCorrect()+"  " );

          }
          System.out.println(" ");

      }
    return gameService.setAnswer(x,y,value);
  }


}
