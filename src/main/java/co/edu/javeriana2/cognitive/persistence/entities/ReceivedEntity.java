package co.edu.javeriana2.cognitive.persistence.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RECEIVED")
public class ReceivedEntity {

    @Id
    @Column(name = "UUID")
    private String uuid;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(
            name = "CREATE_AT",
            columnDefinition = "timestamp",
            insertable = false,
            nullable = false,
            updatable = false
    )
    private Date createdAt;

    @Column(name = "EXTENSION")
    private String extension;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
