package fc.repository;

import fc.model.Scan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends MongoRepository<Scan, String> {

}
