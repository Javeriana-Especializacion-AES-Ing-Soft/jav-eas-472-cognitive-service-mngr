package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import co.edu.javeriana2.cognitive.exceptions.impl.DownloadDocumentException;
import co.edu.javeriana2.cognitive.exceptions.impl.PersistDocumentLogException;
import co.edu.javeriana2.cognitive.exceptions.impl.UploadDocumentException;
import co.edu.javeriana2.cognitive.services.IAwsS3Service;
import co.edu.javeriana2.cognitive.services.ICognitivePersistenceService;
import co.edu.javeriana2.cognitive.utilities.DocumentUtility;
import co.edu.javeriana2.cognitive.utilities.ResourceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CognitiveOcrServiceImplTest {

    @Mock
    private IAwsS3Service awsS3Service;
    @Mock
    private ICognitivePersistenceService cognitivePersistenceService;
    @Mock
    private DocumentUtility documentUtility;

    @InjectMocks
    private CognitiveOcrServiceImpl cognitiveOcrService;

    private final ResourceProvider resourceProvider = new ResourceProvider();

    private DocumentProcessInfoDto documentProcessInfoDto;

    @BeforeEach
    void setUp() throws PersistDocumentLogException {
        MockitoAnnotations.initMocks(this);
        documentProcessInfoDto = resourceProvider.getDocumentProcessInfoDtoMock();
        doNothing().when(cognitivePersistenceService).persistReceived(any());
        doNothing().when(cognitivePersistenceService).persistStored(any());
        when(cognitivePersistenceService.getStoredEntity(any())).thenReturn(resourceProvider.getStoredEntityMock());
    }

    @Test
    void processDocumentTest() throws AbsCognitiveException {
        doNothing().when(documentUtility).validateDocumentExtension(any(), anyString());
        when(awsS3Service.uploadDocumentAndGetObjectKey(any(), any())).thenReturn("guides/2020/08/30/987321342-234-23.jpge");
        CognitiveOcrRsDto cognitiveOcrRsDto = cognitiveOcrService.processDocument(documentProcessInfoDto);
        Assertions.assertNotNull(cognitiveOcrRsDto.getUuid());
    }

    @Test
    void processDocumentFailedByExtensionTest() throws AbsCognitiveException {
        doThrow(UploadDocumentException.class).when(documentUtility).validateDocumentExtension(any(), anyString());
        Assertions.assertThrows(AbsCognitiveException.class, () -> cognitiveOcrService.processDocument(documentProcessInfoDto));
    }

    @Test
    void processDocumentFailedByS3ServiceTest() throws AbsCognitiveException {
        when(awsS3Service.uploadDocumentAndGetObjectKey(any(), any())).thenThrow(UploadDocumentException.class);
        Assertions.assertThrows(AbsCognitiveException.class, () -> cognitiveOcrService.processDocument(documentProcessInfoDto));
    }

    @Test
    void downloadDocumentTest() throws AbsCognitiveException {
        when(awsS3Service.getDocument(anyString(), anyString())).thenReturn("File".getBytes());
        byte[] file = cognitiveOcrService.downloadDocument(UUID.randomUUID(), "bucket");
        Assertions.assertTrue(file.length > 0);
    }

    @Test
    void downloadDocumentFailedByAwsServiceTest() throws AbsCognitiveException {
        when(awsS3Service.getDocument(anyString(), anyString())).thenThrow(DownloadDocumentException.class);
        Assertions.assertThrows(AbsCognitiveException.class, () -> cognitiveOcrService.downloadDocument(UUID.randomUUID(), "bucket"));
    }

    @Test
    void downloadDocumentFailedByNotFoundDocumentTest() throws AbsCognitiveException {
        when(cognitivePersistenceService.getStoredEntity(any())).thenThrow(PersistDocumentLogException.class);
        Assertions.assertThrows(AbsCognitiveException.class, () -> cognitiveOcrService.downloadDocument(UUID.randomUUID(), "bucket"));
    }

}
