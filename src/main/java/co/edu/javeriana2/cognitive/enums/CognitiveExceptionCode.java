package co.edu.javeriana2.cognitive.enums;

import org.springframework.http.HttpStatus;

public enum CognitiveExceptionCode {

    RESTRICTED_BY_POLICY(HttpStatus.NOT_ACCEPTABLE),
    RESTRICTED_BY_INVALID_BODY(HttpStatus.CONFLICT),
    AWS_S3_WITHOUT_COMMUNICATION(HttpStatus.INTERNAL_SERVER_ERROR),
    SQL_WITHOUT_COMMUNICATION(HttpStatus.INTERNAL_SERVER_ERROR);

    private HttpStatus status;

    CognitiveExceptionCode(HttpStatus code) {
        this.status = code;
    }

    public HttpStatus getCode() {
        return status;
    }

}
