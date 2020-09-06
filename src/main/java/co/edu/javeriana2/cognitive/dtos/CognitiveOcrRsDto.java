package co.edu.javeriana2.cognitive.dtos;

import com.amazonaws.services.textract.model.DetectDocumentTextResult;

import java.util.UUID;

public class CognitiveOcrRsDto {

    private UUID uuid;
    private DetectDocumentTextResult cognitiveServiceResponse;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public DetectDocumentTextResult getCognitiveServiceResponse() {
        return cognitiveServiceResponse;
    }

    public void setCognitiveServiceResponse(DetectDocumentTextResult cognitiveServiceResponse) {
        this.cognitiveServiceResponse = cognitiveServiceResponse;
    }
}
