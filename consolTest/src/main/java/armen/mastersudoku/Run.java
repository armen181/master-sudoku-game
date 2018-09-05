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
        System.out.println(gameService.generate(0));



    }

}
