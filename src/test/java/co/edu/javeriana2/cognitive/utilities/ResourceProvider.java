package co.edu.javeriana2.cognitive.utilities;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;
import com.amazonaws.services.s3.model.S3Object;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class ResourceProvider {

    private final byte[] FILE_BODY = new byte[]{60, 104, 116, 109, 108, 32, 108, 97, 110, 103};

    public CognitiveOcrRsDto getCognitiveOcrRsDtoMock() {
        CognitiveOcrRsDto cognitiveOcrRsDto = new CognitiveOcrRsDto();
        cognitiveOcrRsDto.setUuid(UUID.randomUUID());
        cognitiveOcrRsDto.setUuid(UUID.randomUUID());
        return cognitiveOcrRsDto;
    }

    public DocumentProcessInfoDto getDocumentProcessInfoDtoMock() {
        DocumentProcessInfoDto documentProcessInfoDto = new DocumentProcessInfoDto();
        documentProcessInfoDto.setBucketName("472-bucket");
        documentProcessInfoDto.setFileContent(FILE_BODY);
        documentProcessInfoDto.setFileExtension("jpeg");
        documentProcessInfoDto.setRootDirectory("guides");
        documentProcessInfoDto.setExtractType("FORM_DATA");
        return documentProcessInfoDto;
    }

    public S3Object getS3ObjectMock() {
        S3Object s3Object = new S3Object();
        s3Object.setObjectContent(new ByteArrayInputStream("ContenidoDocumento".getBytes()));
        return s3Object;
    }

    public StoredEntity getStoredEntityMock() {
        StoredEntity storedEntity = new StoredEntity();
        storedEntity.setUuid(UUID.randomUUID().toString());
        storedEntity.setObjectKey("guides/file.jpg");
        return storedEntity;
    }

}
