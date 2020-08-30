package co.edu.javeriana2.cognitive.exceptions;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;

public class AbsCognitiveException extends Exception {

    private final CognitiveExceptionCode exceptionCode;

    public AbsCognitiveException(CognitiveExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public AbsCognitiveException(CognitiveExceptionCode exceptionCode, String causeMessage) {
        super(causeMessage);
        this.exceptionCode = exceptionCode;
    }

    public AbsCognitiveException(CognitiveExceptionCode exceptionCode, String causeMessage, Exception e) {
        super(causeMessage, e);
        this.exceptionCode = exceptionCode;
    }

    public CognitiveExceptionCode getExceptionCode() {
        return exceptionCode;
    }

}
