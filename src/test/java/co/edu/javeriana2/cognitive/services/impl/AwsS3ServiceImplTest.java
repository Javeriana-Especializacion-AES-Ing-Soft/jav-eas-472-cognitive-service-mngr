package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import co.edu.javeriana2.cognitive.exceptions.impl.UploadDocumentException;
import co.edu.javeriana2.cognitive.utilities.ResourceProvider;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AwsS3ServiceImplTest {

    @Mock
    private AmazonS3 s3Client;

    @InjectMocks
    private AwsS3ServiceImpl awsS3Service;

    private final ResourceProvider resourceProvider = new ResourceProvider();
    private final UUID uuid = UUID.randomUUID();

    private DocumentProcessInfoDto documentProcessInfoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        awsS3Service.setDefaultRootDirectory("General");
        documentProcessInfoDto = resourceProvider.getDocumentProcessInfoDtoMock();
    }

    @Test
    void uploadDocumentAndGetObjectKeyTest() throws AbsCognitiveException {
        when(s3Client.putObject(any())).thenReturn(null);
        String keyObject = awsS3Service.uploadDocumentAndGetObjectKey(documentProcessInfoDto, uuid);
        Assertions.assertNotNull(keyObject);
    }

    @Test
    void uploadDocumentAndGetObjectKeyNullRootDirectoryTest() throws AbsCognitiveException {
        when(s3Client.putObject(any())).thenReturn(null);
        documentProcessInfoDto.setRootDirectory(null);
        String keyObject = awsS3Service.uploadDocumentAndGetObjectKey(documentProcessInfoDto, uuid);
        Assertions.assertNotNull(keyObject);
    }

    @Test
    void uploadDocumentAndGetObjectKeyFailedByAWSServiceTest() {
        when(s3Client.putObject(any())).thenThrow(AmazonServiceException.class);
        Assertions.assertThrows(UploadDocumentException.class, () -> awsS3Service.uploadDocumentAndGetObjectKey(documentProcessInfoDto, uuid));
    }

    @Test
    void uploadDocumentAndGetObjectKeyFailedByAWSClientTest() {
        when(s3Client.putObject(any())).thenThrow(AmazonClientException.class);
        Assertions.assertThrows(UploadDocumentException.class, () -> awsS3Service.uploadDocumentAndGetObjectKey(documentProcessInfoDto, uuid));
    }

}
