package fc.service.impl;

import fc.service.FileScanResultService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FileScanResultServiceImpl implements FileScanResultService {

    @Override
    @KafkaListener(topics = "av-results", groupId = "results-processor")
    public void processResult(String message) {
        System.out.println("Processing the result from " + message);
    }
}
