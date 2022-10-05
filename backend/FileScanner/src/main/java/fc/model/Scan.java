package fc.model;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "Scan")
public class Scan {

    @Id
    private String scan_id;

    private String permalink;
    private String document_link;
    private LocalDateTime scan_date;
    private int positives;
    private int total;
    private List<ScanResult> scans;

    public Scan(String scan_id, String permalink, String document_link, LocalDateTime scan_date) {
        this.scan_id = scan_id;
        this.permalink = permalink;
        this.document_link = document_link;
        this.scan_date = scan_date;
    }


}
