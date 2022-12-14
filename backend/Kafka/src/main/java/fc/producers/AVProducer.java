package fc.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AVProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishToTopic(String topic, String message) {
        System.out.println("publishing to topic " + topic);
        this.kafkaTemplate.send(topic, message);
    }

}
