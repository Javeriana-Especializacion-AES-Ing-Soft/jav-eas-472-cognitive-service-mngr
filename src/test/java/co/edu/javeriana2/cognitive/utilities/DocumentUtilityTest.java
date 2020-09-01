package co.edu.javeriana2.cognitive.utilities;

import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

class DocumentUtilityTest {

    @InjectMocks
    private DocumentUtility documentUtility;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        documentUtility.setValidExtensions(new String[]{"png", "jpg"});
    }

    @Test
    void validateDocumentExtensionTest() {
        Assertions.assertDoesNotThrow(() -> documentUtility.validateDocumentExtension(UUID.randomUUID(), "png"));
    }

    @Test
    void validateDocumentExtensionFailedByInvalidExtensionTest() {
        Assertions.assertThrows(AbsCognitiveException.class, () -> documentUtility.validateDocumentExtension(UUID.randomUUID(), "pdf"));
    }

}
