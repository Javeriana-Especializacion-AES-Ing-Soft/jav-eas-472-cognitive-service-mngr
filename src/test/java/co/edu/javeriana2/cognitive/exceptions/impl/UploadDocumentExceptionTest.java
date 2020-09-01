package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


class UploadDocumentExceptionTest {

    @Test
    void uploadDocumentException2ConstructorTest() {
        try {
            throw new UploadDocumentException(CognitiveExceptionCode.RESTRICTED_BY_POLICY, "ERROR DE POLICA", new IllegalArgumentException());
        } catch (UploadDocumentException e) {
            Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getExceptionCode().getCode());
        }
    }

}
