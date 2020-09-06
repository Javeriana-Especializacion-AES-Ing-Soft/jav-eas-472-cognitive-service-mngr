package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class DetectTextExceptionTest {

    @Test
    void detectTextExceptionTestConstructorTest() {
        try {
            throw new DetectTextException(CognitiveExceptionCode.AWS_TEXTRACT_WITHOUT_COMMUNICATION, "ERROR TEXTRACT");
        } catch (DetectTextException e) {
            Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getExceptionCode().getCode());
        }
    }

}
