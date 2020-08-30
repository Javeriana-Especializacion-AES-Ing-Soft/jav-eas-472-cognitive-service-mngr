package co.edu.javeriana2.cognitive.exceptions.impl;

import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

public class PersistDocumentLogException extends AbsCognitiveException {

    public PersistDocumentLogException(String causeMessage) {
        super(causeMessage);
    }

    public PersistDocumentLogException(String causeMessage, Exception e) {
        super(causeMessage, e);
    }

}
