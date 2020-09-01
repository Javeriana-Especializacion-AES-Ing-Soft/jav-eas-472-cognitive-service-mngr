package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

public class DownloadDocumentException extends AbsCognitiveException {

    public DownloadDocumentException(CognitiveExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

    public DownloadDocumentException(CognitiveExceptionCode exceptionCode, String causeMessage, Exception e) {
        super(exceptionCode, causeMessage, e);
    }

}
