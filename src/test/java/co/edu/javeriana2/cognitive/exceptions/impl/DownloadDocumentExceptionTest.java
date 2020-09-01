package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class DownloadDocumentExceptionTest {

    @Test
    void downloadDocumentException2ConstructorTest() {
        try {
            throw new DownloadDocumentException(CognitiveExceptionCode.RESTRICTED_BY_POLICY, "ERROR DE POLITICA", new IllegalArgumentException());
        } catch (DownloadDocumentException e) {
            Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getExceptionCode().getCode());
        }
    }

}
