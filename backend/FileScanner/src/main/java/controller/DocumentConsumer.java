package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentConsumer {

    @Autowired
//    AVProducer producer;

    @PostMapping("/file")
    public String hello(@RequestParam(value = "file") MultipartFile multipartFile) {
        return String.format("Hello %s!", multipartFile.getName());

        // Upload to S3 or MongoDB

        // Send message to Kafka


    }

}
