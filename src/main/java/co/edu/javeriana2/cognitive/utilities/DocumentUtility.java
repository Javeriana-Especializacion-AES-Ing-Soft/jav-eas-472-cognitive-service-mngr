package co.edu.javeriana2.cognitive.utilities;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.impl.UploadDocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DocumentUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentUtility.class);

    private List<String> validExtensions;

    public void validateDocumentExtension(UUID documentId, String extension) throws UploadDocumentException {
        LOGGER.info("[DI:{}] inicia validacion de tipo de extension sobre el documento a procesar [E:{}]", documentId, extension);
        if (!validExtensions.contains(extension.toLowerCase())) {
            throw new UploadDocumentException(CognitiveExceptionCode.RESTRICTED_BY_POLICY, "INVALID DOCUMENT EXTENSION");
        }
        LOGGER.info("[DI:{}] finaliza validacion de tipo de extension sobre el documento a procesar", documentId);

    }

    @Value("${cognitive.472.valid.extension}")
    public void setValidExtensions(String[] validExtensions) {
        this.validExtensions = new ArrayList<>(Arrays.asList(validExtensions));
    }

}
