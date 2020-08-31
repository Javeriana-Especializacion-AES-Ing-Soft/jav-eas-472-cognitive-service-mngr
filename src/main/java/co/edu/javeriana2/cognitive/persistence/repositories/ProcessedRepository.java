package co.edu.javeriana2.cognitive.persistence.repositories;

import co.edu.javeriana2.cognitive.persistence.entities.ProcessedEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProcessedRepository extends CrudRepository<ProcessedEntity, String> {

}
