package armen.mastersudoku;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Scanner;

@Slf4j
@Component
public class Run {
    @Autowired
    GameService gameService;


    @EventListener
    public void start(ContextRefreshedEvent contextRefreshedEvent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello");
        for(int i =0; i<9 ;i++) {

            System.out.println(i%3);
            System.out.println(i/3);
        }

        for(int i =0; i<9 ;i++){
            for (int j =0; j<9; j++){
                System.out.printf(" "+gameService.generate().getMatrix()[i][j]+" ");

            }
            System.out.println(" ");

        }
        System.out.println(" ");
        for(int i =0; i<9 ;i++){
            for (int j =0; j<9; j++){
                System.out.printf(" "+gameService.getPlayableMatrix(2)[i][j].getValue()+" ");

            }
            System.out.println(" ");

        }
        System.out.println(" ");

        gameService.setAnswer(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());

        for(int i =0; i<9 ;i++){
            for (int j =0; j<9; j++){
                System.out.printf(" "+gameService.getPlayableMatrix(2)[i][j].getValue()+" "+gameService.getPlayableMatrix(2)[i][j].isCorrect()+"  " );

            }
            System.out.println(" ");

        }
        System.out.println(" ");

        gameService.setAnswer(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());

        for(int i =0; i<9 ;i++){
            for (int j =0; j<9; j++){
                System.out.printf(" "+gameService.getPlayableMatrix(2)[i][j].getValue()+" "+gameService.getPlayableMatrix(2)[i][j].isCorrect()+"  " );

            }
            System.out.println(" ");

        }
        System.out.println(" ");
        //System.out.println(gameService.generate(0).getMatrix()[0][5]+" = "+  gameService.generate(0).getMatrix()[0][6]);




    }

}
