package fc.CrowdstrikeAV;

import com.fasterxml.jackson.databind.ObjectMapper;
import fc.model.Scan;
import fc.model.ScanResult;
import fc.producers.AVProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrowdStrikeScanner {

    @Autowired
    AVProducer producer;

    @KafkaListener(topics = "av-engines", groupId = "CrowdstrikeScanner")
    public void runCrowdStrikeSandbox(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            Scan scan = mapper.readValue(message, Scan.class);
            System.out.println("Consumed message in CrowdStrike Scanner " + scan.getScan_id());

            // Simulate a scan result
            ScanResult scan_result = new ScanResult(scan.getScan_id(), "CrowdStrike Falcon", true, "6.5.2.0.280", "Generic.dx!rkx", LocalDateTime.now());
            producer.publishToTopic("av-results", mapper.writeValueAsString(scan_result));

        } catch (Exception e) {
            System.out.println("An error occurred processing CrowdStike av-engine message");
        }
    }
}
