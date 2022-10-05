package fc.SentinelOneAV;

import fc.producers.AVProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentinelOneScanner {

    @Autowired
    AVProducer producer;

    @KafkaListener(topics = "av-engines", groupId = "sentinelOneScanner")
    public void runCrowdStrikeSandbox(String message) {
        System.out.println("Consumed message in SentinelOne Scanner " + message);
        producer.publishToTopic("av-results", "SentinelOne");
    }
}
