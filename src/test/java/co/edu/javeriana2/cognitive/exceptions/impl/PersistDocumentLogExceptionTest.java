package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class PersistDocumentLogExceptionTest {

    @Test
    void persistDocumentLogException1ConstructorTest() {
        try {
            throw new PersistDocumentLogException(CognitiveExceptionCode.RESTRICTED_BY_POLICY);
        } catch (PersistDocumentLogException e) {
            Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getExceptionCode().getCode());
        }
    }

    @Test
    void persistDocumentLogException3ConstructorTest() {
        try {
            throw new PersistDocumentLogException(CognitiveExceptionCode.RESTRICTED_BY_POLICY, "ERROR DE POLICA", new IllegalArgumentException());
        } catch (PersistDocumentLogException e) {
            Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getExceptionCode().getCode());
        }
    }

}
