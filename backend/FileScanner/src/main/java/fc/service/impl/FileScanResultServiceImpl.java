package fc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fc.model.Scan;
import fc.model.ScanResult;
import fc.service.FileScanResultService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileScanResultServiceImpl implements FileScanResultService {

    @Override
    @KafkaListener(topics = "av-results", groupId = "results-processor")
    public void processResult(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            // ScanResult scan = mapper.readValue(message, ScanResult.class);

            System.out.println("Processing the result from " + message);

        } catch (Exception e) {
            System.out.println("An error occurred processing sentinelOne av-engine message");
        }
    }
}
