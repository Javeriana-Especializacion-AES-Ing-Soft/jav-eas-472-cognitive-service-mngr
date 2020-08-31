package co.edu.javeriana2.cognitive.persistence.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROCESSED")
public class ProcessedEntity {

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

    @Column(name = "TYPE")
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
