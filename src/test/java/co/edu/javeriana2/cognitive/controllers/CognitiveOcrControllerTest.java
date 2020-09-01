package co.edu.javeriana2.cognitive.controllers;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import co.edu.javeriana2.cognitive.exceptions.impl.DownloadDocumentException;
import co.edu.javeriana2.cognitive.exceptions.impl.UploadDocumentException;
import co.edu.javeriana2.cognitive.services.ICognitiveOcrService;
import co.edu.javeriana2.cognitive.utilities.ResourceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CognitiveOcrControllerTest {

    @Mock
    private ICognitiveOcrService cognitiveOcrService;

    @InjectMocks
    private CognitiveOcrController cognitiveOcrController;

    private final ResourceProvider resourceProvider = new ResourceProvider();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void documentReaderTest() throws AbsCognitiveException {
        when(cognitiveOcrService.processDocument(any())).thenReturn(resourceProvider.getCognitiveOcrRsDtoMock());
        ResponseEntity<CognitiveOcrRsDto> response = cognitiveOcrController.documentReader("472-bucket", "FORM",
                "jpge", "guides", new byte[30]);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getUuid());
    }

    @Test
    void documentReaderFailedByExtensionTest() throws AbsCognitiveException {
        when(cognitiveOcrService.processDocument(any())).thenThrow(new UploadDocumentException(CognitiveExceptionCode.RESTRICTED_BY_POLICY, "ERROR EXTENSION"));
        ResponseEntity<CognitiveOcrRsDto> response = cognitiveOcrController.documentReader("472-bucket", "FORM",
                "pdf", "guides", new byte[30]);
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    void documentReaderFailedByDocumentBodyInvalidTest() throws AbsCognitiveException {
        when(cognitiveOcrService.processDocument(any())).thenThrow(new UploadDocumentException(CognitiveExceptionCode.RESTRICTED_BY_INVALID_BODY, "ARCHIVO INVALIDO"));
        ResponseEntity<CognitiveOcrRsDto> response = cognitiveOcrController.documentReader("472-bucket", "FORM",
                "jpge", "guides", new byte[30]);
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void documentReaderFailedByAwsErrorTest() throws AbsCognitiveException {
        when(cognitiveOcrService.processDocument(any())).thenThrow(new UploadDocumentException(CognitiveExceptionCode.AWS_S3_WITHOUT_COMMUNICATION, "S3 CLIENTE SIN CONEXION"));
        ResponseEntity<CognitiveOcrRsDto> response = cognitiveOcrController.documentReader("472-bucket", "FORM",
                "jpge", "guides", new byte[30]);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void downloadDocumentTest() throws AbsCognitiveException {
        when(cognitiveOcrService.downloadDocument(any(), anyString())).thenReturn("file".getBytes());
        ResponseEntity<byte[]> response = cognitiveOcrController.downloadDocument("bucket", UUID.randomUUID());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).length > 0);
    }

    @Test
    void downloadDocumentFailedByDocumentIdNotFoundTest() throws AbsCognitiveException {
        when(cognitiveOcrService.downloadDocument(any(), anyString())).thenThrow(new DownloadDocumentException(CognitiveExceptionCode.DOCUMENT_ID_NOT_FOUND, "NO EXISTE EL ARCHIVO"));
        ResponseEntity<byte[]> response = cognitiveOcrController.downloadDocument("bucket", UUID.randomUUID());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
