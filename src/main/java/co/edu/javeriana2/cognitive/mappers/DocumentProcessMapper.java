package co.edu.javeriana2.cognitive.mappers;

import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.persistence.entities.ProcessedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.ReceivedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;

import java.util.UUID;

public class DocumentProcessMapper {

    private DocumentProcessMapper() {

    }

    public static ReceivedEntity documentProcessMapperInReceivedEntity(DocumentProcessInfoDto documentProcessInfoDto, UUID processId) {
        ReceivedEntity receivedEntity = new ReceivedEntity();
        receivedEntity.setUuid(processId.toString());
        receivedEntity.setExtension(documentProcessInfoDto.getFileExtension().toLowerCase());
        return receivedEntity;
    }

    public static StoredEntity objectKeyInStoredEntity(UUID processId, String objectKey) {
        StoredEntity storedEntity = new StoredEntity();
        storedEntity.setUuid(processId.toString());
        storedEntity.setObjectKey(objectKey);
        return storedEntity;
    }

    public static ProcessedEntity documentProcessMapperInProcessedEntity(DocumentProcessInfoDto documentProcessInfoDto, UUID processId) {
        ProcessedEntity processedEntity = new ProcessedEntity();
        processedEntity.setUuid(processId.toString());
        processedEntity.setType(documentProcessInfoDto.getExtractType());
        return processedEntity;
    }

}
