package co.edu.javeriana2.cognitive.services;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

import java.util.UUID;

public interface ICognitiveOcrService {

    CognitiveOcrRsDto processDocument(DocumentProcessInfoDto documentProcessInfo) throws AbsCognitiveException;

    byte[] downloadDocument(UUID uuid, String bucketName) throws AbsCognitiveException;

}
