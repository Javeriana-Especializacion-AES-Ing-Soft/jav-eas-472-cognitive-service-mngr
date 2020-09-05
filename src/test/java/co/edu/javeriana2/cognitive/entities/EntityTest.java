package co.edu.javeriana2.cognitive.entities;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.persistence.entities.ProcessedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.ReceivedEntity;
import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;
import co.edu.javeriana2.cognitive.utilities.EntityUtilityTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.lang.reflect.InvocationTargetException;


class EntityTest {

    @Test
    void processedEntityTest() throws InvocationTargetException, DatatypeConfigurationException, InstantiationException, IllegalAccessException {
        EntityUtilityTest.testEntity(ProcessedEntity.class);
    }

    @Test
    void receivedEntityTest() throws InvocationTargetException, DatatypeConfigurationException, InstantiationException, IllegalAccessException {
        EntityUtilityTest.testEntity(ReceivedEntity.class);
    }

    @Test
    void storedEntityTest() throws InvocationTargetException, DatatypeConfigurationException, InstantiationException, IllegalAccessException {
        EntityUtilityTest.testEntity(StoredEntity.class);
    }

    @Test
    void cognitiveOcrRsDtoTest() {
        CognitiveOcrRsDto cognitiveOcrRsDto = new CognitiveOcrRsDto();
        cognitiveOcrRsDto.setCognitiveServiceResponse(null);
        Assertions.assertNull(cognitiveOcrRsDto.getCognitiveServiceResponse());
    }

}
