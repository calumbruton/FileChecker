package fc.repository;

import fc.model.Scan;
import fc.model.ScanResult;

/**
 * Used to make custom mongoDB requests that are not covered
 * by the generated methods when extending MongoRepository
 */
public interface ScanRepositoryCustom {

    /**
     * Update the "scans" array in a Scan document by pusing av_scan
     * This is done in a single isolated and atomic transaction using
     * findAndModify
     * @param scan_id The id of the Scan document being updated
     * @param av_scan The resulting scan being appended
     * @return the updated Document
     */
    Scan updateScansUsingFindAndModify(String scan_id, ScanResult av_scan);
}
