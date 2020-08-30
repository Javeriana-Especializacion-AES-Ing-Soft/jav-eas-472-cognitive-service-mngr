package co.edu.javeriana2.cognitive.exceptions;

public class AbsCognitiveException extends Exception {

    public AbsCognitiveException(String causeMessage) {
        super(causeMessage);
    }

    public AbsCognitiveException(String causeMessage, Exception e) {
        super(causeMessage, e);
    }

}
