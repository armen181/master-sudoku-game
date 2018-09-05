package armen.mastersudoku;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class Run {
    @Autowired
    GameService gameService;


    @EventListener
    public void start(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("Hello");


        for(int i =0; i<8 ;i++){
            for (int j =0; j<8; j++){
                System.out.printf(" "+gameService.generate(0).getMatrix()[i][j]+" ");

            }
            System.out.println(" ");

        }

        //System.out.println(gameService.generate(0).getMatrix()[0][5]+" = "+  gameService.generate(0).getMatrix()[0][6]);




    }

}
