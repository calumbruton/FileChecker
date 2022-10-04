package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import producers.AVProducer;

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
    public String report(@RequestParam(value = "file") MultipartFile multipartFile) {

        // Get Metadata from MongoDB

        return String.format("Hello %s!", multipartFile.getName());
    }

}
