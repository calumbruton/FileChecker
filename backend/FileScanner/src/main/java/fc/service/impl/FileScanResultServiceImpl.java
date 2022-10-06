package fc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import fc.model.ScanResult;
import fc.repository.ScanRepository;
import fc.service.FileScanResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FileScanResultServiceImpl implements FileScanResultService {

    @Autowired
    ScanRepository repository;

    @Override
    @KafkaListener(topics = "av-results", groupId = "results-processor")
    public void processResult(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
            ScanResult scan_result = mapper.readValue(message, ScanResult.class);
            String scan_id = scan_result.getScan_id();
            String formattedResult = FormatMessage(scan_result);

            // ScanResult scan = mapper.readValue(message, ScanResult.class);

            System.out.println("Processing the result from " + message);

        } catch (Exception e) {
            System.out.println("An error occurred processing sentinelOne av-engine message");
        }
    }

    private String FormatMessage(ScanResult scan_result) throws com.fasterxml.jackson.core.JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        ImmutableMap<String, ?> fields = ImmutableMap.of(
            "detected", scan_result.getDetected(),
            "version", scan_result.getVersion(),
            "result", scan_result.getResult(),
            "update", scan_result.getUpdate()
        );

        ObjectNode root = mapper.createObjectNode();
        root.put(scan_result.getEngine(), mapper.writeValueAsString(fields));
        return mapper.writeValueAsString(root);
    }
}
