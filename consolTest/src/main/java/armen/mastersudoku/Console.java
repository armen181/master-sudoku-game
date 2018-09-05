package armen.mastersudoku;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Console {
    public static void main(String[] args) {
        log.info("Stone Paper Scissors Game");

        SpringApplication.run(Console.class, args);
    }
}
