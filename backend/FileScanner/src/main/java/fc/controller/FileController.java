package fc.controller;

import fc.producers.AVProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    AVProducer producer;

    @PostMapping("/file/scan")
    public void scan(@RequestParam(value = "file") MultipartFile multipartFile) {

        // Upload to S3 or MongoDB

        // Send message to Kafka
        producer.publishToTopic("av-engines", "calum message");
    }


    @GetMapping("/file/report")
    public String report(@RequestParam(value = "scanId") String scanId) {

        // Get Metadata from MongoDB
        producer.publishToTopic("av-engines", "calum message");

        return String.format("Recovering report information for file scan with id: %s!", scanId);
    }

}
