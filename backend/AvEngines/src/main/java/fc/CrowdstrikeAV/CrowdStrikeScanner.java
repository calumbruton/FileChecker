package fc.CrowdstrikeAV;

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
        System.out.println("Consumed message in CrowdStrike Scanner " + message);
        producer.publishToTopic("av-results", "Crowdstrike");
    }
}
