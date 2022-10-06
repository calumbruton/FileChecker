package fc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fc.model.Scan;
import fc.producers.AVProducer;
import fc.repository.ScanRepository;
import fc.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class FileController {

    @Autowired
    AVProducer producer;

    @Autowired
    ScanRepository repository;

    @PostMapping("/file/scan")
    public ResponseEntity<Object> scan(@RequestParam(value = "file") MultipartFile multipartFile) {
        try {
            String scan_id = new RandomString().nextString();
            String permalink = "https://localhost:3000/file/report/" + scan_id;
            String document_link = "https://anS3bucketLinkToTheDocument";
            LocalDateTime date_time = LocalDateTime.now();

            Scan scan = new Scan(scan_id, permalink, document_link, date_time);

            // Upload to S3 and MongoDB
            repository.insert(scan);

            // Send message to Kafka for AV Engines to Consume
            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
            String scanJson = mapper.writeValueAsString(scan);
            producer.publishToTopic("av-engines", scanJson);

            // Create response json
            ObjectNode response = mapper.valueToTree(scan);
            response.remove("positives");
            response.remove("total");
            response.remove("scans");
            response.remove("scan_date");
            response.put("response_code", 1);
            response.put("verbose_msg","Scan request successfully queued, come back later for the report" );
            return new ResponseEntity<>(response.toPrettyString(), HttpStatus.OK);

        } catch (Exception e){
            System.out.println("Error occurred " + e);
            return new ResponseEntity<>("Scan request failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/file/report")
    public ResponseEntity<Object> report(@RequestParam String scan_Id) {

        // Get Metadata from MongoDB
        Optional<Scan> scan_document = repository.findById(scan_Id);
        if (!scan_document.isPresent()) {
            return new ResponseEntity<>("Scan report failed, no scan with id " + scan_Id, HttpStatus.BAD_REQUEST);
        }

        // Create response json
        Scan scan = scan_document.get();
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        ObjectNode response = mapper.valueToTree(scan);
        response.put("response_code", 1);
        response.put("verbose_msg","Scan finished, scan information embedded in this object" );
        return new ResponseEntity<>(response.toPrettyString(), HttpStatus.OK);
    }

}
