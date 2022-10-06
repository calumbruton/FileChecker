package fc.repository.impl;

import com.mongodb.client.result.UpdateResult;
import fc.model.Scan;
import fc.model.ScanResult;
import fc.repository.ScanRepositoryCustom;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ScanRepositoryCustomImpl implements ScanRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public ScanRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Scan updateScansUsingFindAndModify(String scan_id, ScanResult av_scan) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(scan_id));
        Update updateDefinition = new Update().push("scans", av_scan);
        updateDefinition.inc("total");
        if (av_scan.getDetected()) {
            updateDefinition.inc("positives");
        }
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(false);

        return mongoTemplate.findAndModify(query, updateDefinition, options, Scan.class);
    }
}
