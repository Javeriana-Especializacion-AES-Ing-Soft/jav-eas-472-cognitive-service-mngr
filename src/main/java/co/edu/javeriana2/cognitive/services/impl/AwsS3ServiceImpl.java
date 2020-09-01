package co.edu.javeriana2.cognitive.services.impl;

import co.edu.javeriana2.cognitive.dtos.DocumentProcessInfoDto;
import co.edu.javeriana2.cognitive.enums.CognitiveExceptionCode;
import co.edu.javeriana2.cognitive.exceptions.AbsCognitiveException;
import co.edu.javeriana2.cognitive.exceptions.impl.UploadDocumentException;
import co.edu.javeriana2.cognitive.services.IAwsS3Service;
import co.edu.javeriana2.cognitive.utilities.DateUtility;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
public class AwsS3ServiceImpl implements IAwsS3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(AwsS3ServiceImpl.class);

    protected AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

    private String defaultRootDirectory;

    @Override
    public String uploadDocumentAndGetObjectKey(DocumentProcessInfoDto documentProcessInfo, UUID documentId) throws AbsCognitiveException {
        LOGGER.info("[DI:{}] inicia proceso de carga de documento a s3", DateUtility.getNowInLocalDateTime());
        String objectKey;
        try (InputStream fileBody = new ByteArrayInputStream(documentProcessInfo.getFileContent())) {
            String bucket = documentProcessInfo.getBucketName();
            String inRootDirectory = documentProcessInfo.getRootDirectory();
            String rootDirectory = Objects.isNull(inRootDirectory) ? defaultRootDirectory : inRootDirectory;
            String extension = documentProcessInfo.getFileExtension().toLowerCase();
            objectKey = rootDirectory.concat("/").concat(DateUtility.getCurrentDateInFormat("yyyy/MM/dd"))
                    .concat("/")
                    .concat(documentId.toString()).concat(".").concat(extension);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(documentProcessInfo.getFileContent().length);
            PutObjectRequest requestFile = new PutObjectRequest(bucket, objectKey, fileBody, objectMetadata);
            s3Client.putObject(requestFile);
        } catch (IOException e) {
            throw new UploadDocumentException(CognitiveExceptionCode.RESTRICTED_BY_INVALID_BODY, String.format("[DI:%s] Documento corrupto en el cuerpo.", documentId.toString()));
        } catch (AmazonServiceException ase) {
            throw new UploadDocumentException(CognitiveExceptionCode.AWS_S3_WITHOUT_COMMUNICATION, String.format("[DI:%s] Error de servicio en carga documento en AWS.", documentId.toString()));
        } catch (AmazonClientException ace) {
            throw new UploadDocumentException(CognitiveExceptionCode.AWS_S3_WITHOUT_COMMUNICATION, String.format("[DI:%s] Error de solicitud en carga documento en AWS.", documentId.toString()));
        }
        LOGGER.info("[DI:{}] finaliza proceso de carga de documento a s3", DateUtility.getNowInLocalDateTime());
        return objectKey;
    }

    @Value("${cognitive.472.s3.default.root.directory}")
    public void setDefaultRootDirectory(String defaultRootDirectory) {
        this.defaultRootDirectory = defaultRootDirectory;
    }
}
