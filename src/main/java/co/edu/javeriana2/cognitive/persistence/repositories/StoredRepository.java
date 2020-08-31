package co.edu.javeriana2.cognitive.persistence.repositories;

import co.edu.javeriana2.cognitive.persistence.entities.StoredEntity;
import org.springframework.data.repository.CrudRepository;

public interface StoredRepository extends CrudRepository<StoredEntity, String> {

}
