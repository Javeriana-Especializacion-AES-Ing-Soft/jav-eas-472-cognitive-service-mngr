package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.impl.PersistDocumentLogException;
import co.edu.javeriana2.cognitive.mappers.DocumentProcessMapper;
import co.edu.javeriana2.cognitive.persistence.entities.ProcessedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.ReceivedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;
import co.edu.javeriana2.cognitive.persistence.repositories.ProcessedRepository;
import co.edu.javeriana2.cognitive.persistence.repositories.ReceivedRepository;
import co.edu.javeriana2.cognitive.persistence.repositories.StoredRepository;
import co.edu.javeriana2.cognitive.utilities.ResourceProvider;
import org.hibernate.tool.schema.ast.SqlScriptParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CognitivePersistenceServiceImplTest {

    @Mock
    private ProcessedRepository processedRepository;
    @Mock
    private ReceivedRepository receivedRepository;
    @Mock
    private StoredRepository storedRepository;

    @InjectMocks
    private CognitivePersistenceServiceImpl cognitivePersistenceService;

    private final ResourceProvider resourceProvider = new ResourceProvider();

    private DocumentProcessInfoDto documentProcessInfoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        documentProcessInfoDto = resourceProvider.getDocumentProcessInfoDtoMock();
    }

    @Test
    void persistReceivedTest() {
        when(receivedRepository.save(any())).thenReturn(null);
        ReceivedEntity receivedEntity = DocumentProcessMapper.documentProcessMapperInReceivedEntity(documentProcessInfoDto, UUID.randomUUID());
        Assertions.assertDoesNotThrow(() -> cognitivePersistenceService.persistReceived(receivedEntity));
    }

    @Test
    void persistReceivedFailedTest() {
        when(receivedRepository.save(any())).thenThrow(SqlScriptParserException.class);
        ReceivedEntity receivedEntity = DocumentProcessMapper.documentProcessMapperInReceivedEntity(documentProcessInfoDto, UUID.randomUUID());
        Assertions.assertThrows(PersistDocumentLogException.class, () -> cognitivePersistenceService.persistReceived(receivedEntity));
    }

    @Test
    void persistStoredTest() {
        when(storedRepository.save(any())).thenReturn(null);
        StoredEntity storedEntity = DocumentProcessMapper.objectKeyInStoredEntity(UUID.randomUUID(), "guides/asd.jpg");
        Assertions.assertDoesNotThrow(() -> cognitivePersistenceService.persistStored(storedEntity));
    }

    @Test
    void persistStoredFailedTest() {
        when(storedRepository.save(any())).thenThrow(SqlScriptParserException.class);
        StoredEntity storedEntity = DocumentProcessMapper.objectKeyInStoredEntity(UUID.randomUUID(), "guides/asd.jpg");
        Assertions.assertThrows(PersistDocumentLogException.class, () -> cognitivePersistenceService.persistStored(storedEntity));
    }

    @Test
    void persistProcessedTest() {
        when(processedRepository.save(any())).thenReturn(null);
        ProcessedEntity processedEntity = DocumentProcessMapper.documentProcessMapperInProcessedEntity(documentProcessInfoDto, UUID.randomUUID());
        Assertions.assertDoesNotThrow(() -> cognitivePersistenceService.persistProcessed(processedEntity));
    }

    @Test
    void persistProcessedFailedTest() {
        when(processedRepository.save(any())).thenThrow(SqlScriptParserException.class);
        ProcessedEntity processedEntity = DocumentProcessMapper.documentProcessMapperInProcessedEntity(documentProcessInfoDto, UUID.randomUUID());
        Assertions.assertThrows(PersistDocumentLogException.class, () -> cognitivePersistenceService.persistProcessed(processedEntity));
    }

    @Test
    void getStoredEntityTest() throws PersistDocumentLogException {
        when(storedRepository.findById(anyString())).thenReturn(Optional.of(new StoredEntity()));
        StoredEntity storedEntity = cognitivePersistenceService.getStoredEntity(UUID.randomUUID().toString());
        Assertions.assertNotNull(storedEntity);
    }

    @Test
    void getStoredEntityFailedByUuidNotFoundTest() {
        when(storedRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(PersistDocumentLogException.class, () -> cognitivePersistenceService.getStoredEntity(UUID.randomUUID().toString()));
    }


}
