package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

public class UploadDocumentException extends AbsCognitiveException {

    public UploadDocumentException(CognitiveExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

    public UploadDocumentException(CognitiveExceptionCode exceptionCode, String causeMessage, Exception e) {
        super(exceptionCode, causeMessage, e);
    }

}
