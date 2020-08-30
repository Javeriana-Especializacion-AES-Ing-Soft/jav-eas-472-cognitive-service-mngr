package co.edu.javeriana2.cognitive.utilities;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;

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

}
