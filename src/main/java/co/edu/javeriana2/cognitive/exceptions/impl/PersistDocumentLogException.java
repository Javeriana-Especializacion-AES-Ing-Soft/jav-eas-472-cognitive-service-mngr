package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

public class PersistDocumentLogException extends AbsCognitiveException {

    public PersistDocumentLogException(CognitiveExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public PersistDocumentLogException(CognitiveExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

    public PersistDocumentLogException(CognitiveExceptionCode exceptionCode, String causeMessage, Exception e) {
        super(exceptionCode, causeMessage, e);
    }

}
