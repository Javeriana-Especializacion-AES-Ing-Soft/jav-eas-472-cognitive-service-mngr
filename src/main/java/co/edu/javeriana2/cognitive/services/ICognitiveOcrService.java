package co.edu.javeriana2.cognitive.services;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;

public interface ICognitiveOcrService {

    CognitiveOcrRsDto processDocument(DocumentProcessInfoDto documentProcessInfo) throws AbsCognitiveException;

}
