package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.impl.PersistDocumentLogException;
import co.edu.javeriana2.cognitive.persistence.entities.ProcessedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.ReceivedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;
import co.edu.javeriana2.cognitive.persistence.repositories.ProcessedRepository;
import co.edu.javeriana2.cognitive.persistence.repositories.ReceivedRepository;
import co.edu.javeriana2.cognitive.persistence.repositories.StoredRepository;
import co.edu.javeriana2.cognitive.services.ICognitivePersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CognitivePersistenceServiceImpl implements ICognitivePersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CognitivePersistenceServiceImpl.class);
    private static final String DEFAULT_PERSIST_ERROR = "Error al persistir la entidad [%s]";

    private ProcessedRepository processedRepository;
    private ReceivedRepository receivedRepository;
    private StoredRepository storedRepository;

    @Override
    public void persistReceived(ReceivedEntity receivedEntity) throws PersistDocumentLogException {
        LOGGER.info("[DI:{}] inicia proceso de almacenamiento de registro recibido", receivedEntity.getUuid());
        try {
            receivedRepository.save(receivedEntity);
        } catch (Exception e) {
            throw new PersistDocumentLogException(CognitiveExceptionCode.SQL_WITHOUT_COMMUNICATION, String.format(DEFAULT_PERSIST_ERROR, "RECEIVED"));
        }
        LOGGER.info("[DI:{}] finaliza proceso de almacenamiento de registro recibido", receivedEntity.getUuid());
    }

    @Override
    public void persistStored(StoredEntity storedEntity) throws PersistDocumentLogException {
        LOGGER.info("[DI:{}] inicia proceso de almacenamiento de registro almacenado", storedEntity.getUuid());
        try {
            storedRepository.save(storedEntity);
        } catch (Exception e) {
            throw new PersistDocumentLogException(CognitiveExceptionCode.SQL_WITHOUT_COMMUNICATION, String.format(DEFAULT_PERSIST_ERROR, "DOCUMENT_STORED"));
        }
        LOGGER.info("[DI:{}] finaliza proceso de almacenamiento de registro almacenado", storedEntity.getUuid());
    }

    @Override
    public void persistProcessed(ProcessedEntity processedEntity) throws PersistDocumentLogException {
        LOGGER.info("[DI:{}] inicia proceso de almacenamiento de registro almacenado", processedEntity.getUuid());
        try {
            processedRepository.save(processedEntity);
        } catch (Exception e) {
            throw new PersistDocumentLogException(CognitiveExceptionCode.SQL_WITHOUT_COMMUNICATION, String.format(DEFAULT_PERSIST_ERROR, "PROCESSED"));
        }
        LOGGER.info("[DI:{}] finaliza proceso de almacenamiento de registro almacenado", processedEntity.getUuid());
    }

    @Override
    public StoredEntity getStoredEntity(UUID uuid) throws PersistDocumentLogException {
        LOGGER.info("[DI:{}] inicia proceso de recuperacion de registro almacenado", uuid);
        Optional<StoredEntity> stored = storedRepository.findById(uuid.toString());
        StoredEntity documentStoredInfo = stored
                .orElseThrow(() -> new PersistDocumentLogException(CognitiveExceptionCode.DOCUMENT_ID_NOT_FOUND, String.format("[DI:%s] ID NO ENCONTRADO", uuid)));
        LOGGER.info("[DI:{}] finaliza proceso de recuperacion de registro almacenado", uuid);
        return documentStoredInfo;
    }

    @Autowired
    public void setProcessedRepository(ProcessedRepository processedRepository) {
        this.processedRepository = processedRepository;
    }

    @Autowired
    public void setReceivedRepository(ReceivedRepository receivedRepository) {
        this.receivedRepository = receivedRepository;
    }

    @Autowired
    public void setStoredRepository(StoredRepository storedRepository) {
        this.storedRepository = storedRepository;
    }

}
