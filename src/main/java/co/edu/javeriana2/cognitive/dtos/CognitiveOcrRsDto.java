package co.edu.javeriana2.cognitive.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

public class CognitiveOcrRsDto {

    private UUID uuid;
    private JsonNode cognitiveServiceResponse;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public JsonNode getCognitiveServiceResponse() {
        return cognitiveServiceResponse;
    }

    public void setCognitiveServiceResponse(JsonNode cognitiveServiceResponse) {
        this.cognitiveServiceResponse = cognitiveServiceResponse;
    }

}
