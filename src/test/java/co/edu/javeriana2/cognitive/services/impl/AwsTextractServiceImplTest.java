package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.exceptions.impl.DetectTextException;
import com.amazonaws.services.textract.AmazonTextract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AwsTextractServiceImplTest {

    @Mock
    protected AmazonTextract awsTextractClient;

    @InjectMocks
    private AwsTextractServiceImpl awsTextractService;

    private final static String BUCKET = "guides";
    private final static String OBJECT_PATH = "asddsa.png";
    private final static UUID UUID_RANDOM = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void callServiceTest() {
        when(awsTextractClient.detectDocumentText(any())).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> awsTextractService.callService(BUCKET, OBJECT_PATH, UUID_RANDOM));
    }

    @Test
    void callServiceFailedByTextractServiceTest() {
        when(awsTextractClient.detectDocumentText(any())).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(DetectTextException.class, () -> awsTextractService.callService(BUCKET, OBJECT_PATH, UUID_RANDOM));
    }

}
