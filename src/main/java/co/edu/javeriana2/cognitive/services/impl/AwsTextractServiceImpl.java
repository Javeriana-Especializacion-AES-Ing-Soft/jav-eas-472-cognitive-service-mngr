package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.impl.DetectTextException;
import co.edu.javeriana2.cognitive.services.IAwsTextractService;
import co.edu.javeriana2.cognitive.utilities.JsonUtility;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;
import com.amazonaws.services.textract.model.S3Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AwsTextractServiceImpl implements IAwsTextractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AwsTextractServiceImpl.class);

    protected AmazonTextract awsTextractClient = AmazonTextractClientBuilder.defaultClient();

    @Override
    public DetectDocumentTextResult callService(String bucketName, String objectKey, UUID documentId) throws DetectTextException {
        LOGGER.info("[DI:{}] inicia proceso de solicitud de analisis de documento a textract", documentId);
        try {
            DetectDocumentTextRequest request = new DetectDocumentTextRequest()
                    .withDocument(new Document()
                            .withS3Object(new S3Object()
                                    .withName(objectKey)
                                    .withBucket(bucketName)));
            DetectDocumentTextResult detectDocumentTextResult = awsTextractClient.detectDocumentText(request);
            LOGGER.info("[DI:{}] finaliza proceso de solicitud de analisis de documento a textract [TEXTO:{}]", documentId, JsonUtility.getPlainJson(detectDocumentTextResult));
            return detectDocumentTextResult;
        } catch (Exception e) {
            throw new DetectTextException(CognitiveExceptionCode.AWS_TEXTRACT_WITHOUT_COMMUNICATION, String.format("[DI:%s] Error de servicio en detección de texto en documento de AWS.", documentId.toString()), e);
        }
    }

}
