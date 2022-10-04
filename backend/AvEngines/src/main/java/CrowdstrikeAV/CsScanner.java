package CrowdstrikeAV;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CsScanner {

    @KafkaListener(topics = "av-engines", groupId = "av-engines-group")
    public void runCrowdStrikeSandbox(String message) {
        System.out.println("Consumed message in CrowdStrike Scanner " + message);
    }
}
