package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import co.edu.javeriana2.cognitive.services.IAwsS3Service;
import co.edu.javeriana2.cognitive.services.ICognitiveOcrService;
import co.edu.javeriana2.cognitive.utilities.DocumentUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CognitiveOcrServiceImpl implements ICognitiveOcrService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CognitiveOcrServiceImpl.class);

    private IAwsS3Service awsS3Service;

    private DocumentUtility documentUtility;

    @Override
    public CognitiveOcrRsDto processDocument(DocumentProcessInfoDto documentProcessInfo) throws AbsCognitiveException {
        UUID processId = UUID.randomUUID();
        LOGGER.info("[ID:{}] inicia proceso de validacion, carga y registro en log.", processId);
        documentUtility.validateDocumentExtension(documentProcessInfo.getFileExtension());
        awsS3Service.uploadDocumentAndGetObjectKey(documentProcessInfo, processId);
        LOGGER.info("[ID:{}] finaliza proceso de validacion, carga y registro en log.", processId);
        return null;
    }

    @Autowired
    public void setAwsS3Service(IAwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @Autowired
    public void setDocumentUtility(DocumentUtility documentUtility) {
        this.documentUtility = documentUtility;
    }

}
