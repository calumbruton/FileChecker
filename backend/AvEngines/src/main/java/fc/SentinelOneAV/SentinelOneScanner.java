package fc.SentinelOneAV;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fc.model.Scan;
import fc.model.ScanResult;
import fc.producers.AVProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SentinelOneScanner {

    @Autowired
    AVProducer producer;

    @KafkaListener(topics = "av-engines", groupId = "sentinelOneScanner")
    public void runCrowdStrikeSandbox(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            Scan scan = mapper.readValue(message, Scan.class);
            System.out.println("Consumed message in SentinelOne Scanner " + scan.getScan_id());

            // Simulate a scan result
            ScanResult scan_result = new ScanResult(true, "v1", "Trojan.VB.acgy", LocalDateTime.now());
            ObjectNode node = mapper.valueToTree(scan_result);
            node.put("scan_id", scan.getScan_id());
            producer.publishToTopic("av-results", node.toPrettyString());

        } catch (Exception e) {
            System.out.println("An error occurred processing sentinelOne av-engine message");
        }
    }
}
