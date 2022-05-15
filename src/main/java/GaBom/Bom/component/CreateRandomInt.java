package GaBom.Bom.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateRandomInt {


    public String createRandomInt(Long userNo){
        Random random = new Random();
        StringBuilder randomIntString = new StringBuilder();

        for(int i = 0; i < 6; i++){
            Integer nextInt = random.nextInt(9);
            randomIntString.append(nextInt.toString());
        }

        log.info(randomIntString.toString());

        return randomIntString.toString();
    }
}
