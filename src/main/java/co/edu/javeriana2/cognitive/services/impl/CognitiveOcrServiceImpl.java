package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import co.edu.javeriana2.cognitive.mappers.DocumentProcessMapper;
import co.edu.javeriana2.cognitive.persistence.entities.ProcessedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.ReceivedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;
import co.edu.javeriana2.cognitive.services.IAwsS3Service;
import co.edu.javeriana2.cognitive.services.IAwsTextractService;
import co.edu.javeriana2.cognitive.services.ICognitiveOcrService;
import co.edu.javeriana2.cognitive.services.ICognitivePersistenceService;
import co.edu.javeriana2.cognitive.utilities.DocumentUtility;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CognitiveOcrServiceImpl implements ICognitiveOcrService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CognitiveOcrServiceImpl.class);

    private IAwsS3Service awsS3Service;
    private IAwsTextractService awsTextractService;
    private ICognitivePersistenceService cognitivePersistenceService;

    private DocumentUtility documentUtility;

    @Override
    public CognitiveOcrRsDto processDocument(DocumentProcessInfoDto documentProcessInfo) throws AbsCognitiveException {
        UUID processId = UUID.randomUUID();
        CognitiveOcrRsDto cognitiveOcrRsDto = new CognitiveOcrRsDto();
        cognitiveOcrRsDto.setUuid(processId);
        LOGGER.info("[DI:{}] inicia proceso de validacion, carga y registro en log.", processId);
        ReceivedEntity receivedEntity = DocumentProcessMapper.documentProcessMapperInReceivedEntity(documentProcessInfo, processId);
        cognitivePersistenceService.persistReceived(receivedEntity);
        documentUtility.validateDocumentExtension(processId, documentProcessInfo.getFileExtension());
        String objectKey = awsS3Service.uploadDocumentAndGetObjectKey(documentProcessInfo, processId);
        StoredEntity storedEntity = DocumentProcessMapper.objectKeyInStoredEntity(processId, objectKey);
        cognitivePersistenceService.persistStored(storedEntity);
        DetectDocumentTextResult detectDocumentTextResult = awsTextractService.callService(documentProcessInfo.getBucketName(), objectKey, processId);
        cognitiveOcrRsDto.setCognitiveServiceResponse(detectDocumentTextResult);
        ProcessedEntity processedEntity = DocumentProcessMapper.documentProcessMapperInProcessedEntity(documentProcessInfo, processId);
        cognitivePersistenceService.persistProcessed(processedEntity);
        LOGGER.info("[DI:{}] finaliza proceso de validacion, carga y registro en log.", processId);
        return cognitiveOcrRsDto;
    }

    @Override
    public byte[] downloadDocument(String uuid, String bucketName) throws AbsCognitiveException {
        LOGGER.info("[DI:{}] inicia proceso de validacion de registro de documento y descarga.", uuid);
        StoredEntity storedEntity = cognitivePersistenceService.getStoredEntity(uuid);
        byte[] documentBody = awsS3Service.getDocument(bucketName, storedEntity.getObjectKey());
        LOGGER.info("[DI:{}] finaliza proceso de validacion de registro de documento y descarga.", uuid);
        return documentBody;
    }

    @Autowired
    public void setAwsS3Service(IAwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @Autowired
    public void setAwsTextractService(IAwsTextractService awsTextractService) {
        this.awsTextractService = awsTextractService;
    }

    @Autowired
    public void setDocumentUtility(DocumentUtility documentUtility) {
        this.documentUtility = documentUtility;
    }

    @Autowired
    public void setCognitivePersistenceService(ICognitivePersistenceService cognitivePersistenceService) {
        this.cognitivePersistenceService = cognitivePersistenceService;
    }
}
