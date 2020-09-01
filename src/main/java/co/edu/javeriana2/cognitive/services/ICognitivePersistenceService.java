package co.edu.javeriana2.cognitive.services;

import co.edu.javeriana2.cognitive.exceptions.impl.PersistDocumentLogException;
import co.edu.javeriana2.cognitive.persistence.entities.ProcessedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.ReceivedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;

public interface ICognitivePersistenceService {

    void persistReceived(ReceivedEntity receivedEntity) throws PersistDocumentLogException;

    void persistStored(StoredEntity receivedEntity) throws PersistDocumentLogException;

    void persistProcessed(ProcessedEntity receivedEntity) throws PersistDocumentLogException;

    StoredEntity getStoredEntity(String uuid) throws PersistDocumentLogException;

}
