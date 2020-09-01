package co.edu.javeriana2.cognitive.services;

import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

import java.util.UUID;

public interface IAwsS3Service {

    String uploadDocumentAndGetObjectKey(DocumentProcessInfoDto documentProcessInfo, UUID documentId) throws AbsCognitiveException;

    byte[] getDocument(String bucketName, String objectKey) throws AbsCognitiveException;

}
