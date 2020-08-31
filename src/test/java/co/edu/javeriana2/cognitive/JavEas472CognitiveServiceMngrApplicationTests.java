package co.edu.javeriana2.cognitive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavEas472CognitiveServiceMngrApplicationTests {

    @Test
    void contextLoads() {
        String test = "main test";
        Assertions.assertDoesNotThrow(() -> JavEas472CognitiveServiceMngrApplication.main(new String[]{"args"}));
    }

}
