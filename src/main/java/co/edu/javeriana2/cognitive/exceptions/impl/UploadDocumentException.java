package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

public class UploadDocumentException extends AbsCognitiveException {

    public UploadDocumentException(String causeMessage) {
        super(causeMessage);
    }

    public UploadDocumentException(String causeMessage, Exception e) {
        super(causeMessage, e);
    }

}
