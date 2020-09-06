package co.edu.javeriana2.cognitive.services;

import co.edu.javeriana2.cognitive.exceptions.impl.DetectTextException;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;

import java.util.UUID;

public interface IAwsTextractService {

    DetectDocumentTextResult callService(String bucketName, String objectKey, UUID documentId) throws DetectTextException;

}
