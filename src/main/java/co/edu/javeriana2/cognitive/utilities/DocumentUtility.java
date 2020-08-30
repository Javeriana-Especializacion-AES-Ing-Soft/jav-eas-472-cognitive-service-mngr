package co.edu.javeriana2.cognitive.utilities;

import co.edu.javeriana2.cognitive.exceptions.impl.UploadDocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DocumentUtility {

    private List<String> validExtensions;

    public void validateDocumentExtension(String extension) throws UploadDocumentException {
        if (!validExtensions.contains(extension.toLowerCase())) {
            throw new UploadDocumentException("INVALID DOCUMENT EXTENSION");
        }

    }

    @Value("${cognitive.472.valid.extension}")
    public void setValidExtensions(String[] validExtensions) {
        this.validExtensions = new ArrayList<>(Arrays.asList(validExtensions));
    }

}
