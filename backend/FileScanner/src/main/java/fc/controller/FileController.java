package fc.controller;

import fc.model.Scan;
import fc.producers.AVProducer;
import fc.repository.ScanRepository;
import fc.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
public class FileController {

    @Autowired
    AVProducer producer;

    @Autowired
    ScanRepository repository;

    @PostMapping("/file/scan")
    public void scan(@RequestParam(value = "file") MultipartFile multipartFile) {
//        {
//         'permalink': 'https://www.virustotal.com/file/d140c...244ef892e5/analysis/1359112395/',
//         'resource': 'd140c244ef892e59c7f68bd0c6f74bb711032563e2a12fa9dda5b760daecd556',
//         'response_code': 1,
//         'scan_id': 'd140c244ef892e59c7f68bd0c6f74bb711032563e2a12fa9dda5b760daecd556-1359112395',
//         'verbose_msg': 'Scan request successfully queued, come back later for the report',
//         'sha256': 'd140c244ef892e59c7f68bd0c6f74bb711032563e2a12fa9dda5b760daecd556'
//        }

        String scan_id = new RandomString().nextString();
        String permalink = "https://localhost:3000/file/report/" + scan_id;
        String document_link = "https://anS3bucketLinkToTheDocument";
        String response_code = "1";
        String verbose_msg = "Scan request successfully queued, come back later for the report";
        LocalDateTime date_time = LocalDateTime.now();

        Scan scan = new Scan(scan_id, permalink, document_link, date_time);

        // Upload to S3 and MongoDB
        repository.save(scan);

        // Send message to Kafka
        producer.publishToTopic("av-engines", "calum message");

        repository.findById(scan_id);
    }


    @GetMapping("/file/report")
    public String report(@RequestParam(value = "scanId") String scanId) {

        // Get Metadata from MongoDB
        producer.publishToTopic("av-engines", "calum message");

        return String.format("Recovering report information for file scan with id: %s!", scanId);
    }

}
