package fc.CrowdstrikeAV;

import com.fasterxml.jackson.databind.ObjectMapper;
import fc.model.Scan;
import fc.producers.AVProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
            producer.publishToTopic("av-results", "Crowdstrike");
        } catch (Exception e) {
            System.out.println("An error occured processing av-engine message");
        }
    }
}
