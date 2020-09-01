package co.edu.javeriana2.cognitive.controllers;

import co.edu.javeriana2.cognitive.dtos.CognitiveOcrRsDto;
import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import co.edu.javeriana2.cognitive.services.ICognitiveOcrService;
import co.edu.javeriana2.cognitive.utilities.DateUtility;
import co.edu.javeriana2.cognitive.utilities.SanitizeString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("*") // NO SONAR
@RequestMapping("/V1/Utilities/documents")
@RestController
public class CognitiveOcrController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CognitiveOcrController.class);

    private ICognitiveOcrService cognitiveOcrService;

    @PostMapping("/read")
    public ResponseEntity<CognitiveOcrRsDto> documentReader(@RequestHeader(name = "X-Bucket-Name") String bucketName,
                                                            @RequestHeader(name = "X-Extract-Type") String extractType,
                                                            @RequestHeader(name = "X-File-Extension") String fileExtension,
                                                            @RequestHeader(name = "X-Root-Directory", required = false) String rootDirectory,
                                                            @RequestBody byte[] fileContent) {
        LOGGER.info("INICIA PROCESO DE RECONOCIMIENTO COGNITIVO PARA LECTURA DE DOCUMENTOS [DT:{}]", DateUtility.getNowInLocalDateTime());
        DocumentProcessInfoDto documentProcessInfo = new DocumentProcessInfoDto();
        documentProcessInfo.setBucketName(bucketName);
        documentProcessInfo.setExtractType(extractType);
        documentProcessInfo.setFileContent(fileContent);
        documentProcessInfo.setFileExtension(SanitizeString.sanitize(fileExtension));
        documentProcessInfo.setRootDirectory(rootDirectory);
        try {
            CognitiveOcrRsDto response = cognitiveOcrService.processDocument(documentProcessInfo);
            LOGGER.info("FINALIZA PROCESO DE RECONOCIMIENTO COGNITIVO PARA LECTURA DE DOCUMENTOS [DT:{}]", DateUtility.getNowInLocalDateTime());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AbsCognitiveException e) {
            LOGGER.error("ERROR GENERADO: ", e);
            return new ResponseEntity<>(e.getExceptionCode().getCode());
        }
    }

    @GetMapping("/{document-uuid}")
    public ResponseEntity<byte[]> downloadDocument(@RequestHeader(name = "X-Bucket-Name") String bucketName,
                                                   @PathVariable("document-uuid") UUID uuid) {
        LOGGER.info("[DT:{}] INICIA PROCESO DE RECUPERACION DE DOCUMENTOS [DT:{}]", uuid, DateUtility.getNowInLocalDateTime());
        try {
            byte[] response = cognitiveOcrService.downloadDocument(uuid, bucketName);
            LOGGER.info("[DT:{}] FINALIZA PROCESO DE RECUPERACION DE DOCUMENTOS [DT:{}]", uuid, DateUtility.getNowInLocalDateTime());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AbsCognitiveException e) {
            LOGGER.error("ERROR GENERADO: ", e);
            return new ResponseEntity<>(e.getExceptionCode().getCode());
        }
    }

    @Autowired
    public void setCognitiveOcrService(ICognitiveOcrService cognitiveOcrService) {
        this.cognitiveOcrService = cognitiveOcrService;
    }
}
