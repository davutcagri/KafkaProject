package davutcagri.KafkaProducer.kafka;

import davutcagri.KafkaProducer.dto.CourierDTO;
import davutcagri.KafkaProducer.entity.Courier;
import davutcagri.KafkaProducer.service.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class KafkaProducer {
    private final CourierService courierService;
    private final NewTopic topic;
    private final KafkaTemplate<Long, CourierDTO> kafkaTemplate;

    public KafkaProducer(CourierService courierService, NewTopic topic, KafkaTemplate<Long, CourierDTO> kafkaTemplate) {
        this.courierService = courierService;
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void produceCourier() {
        Courier courier = courierService.createCouirer();

        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setCourierId(courier.getId());
        courierDTO.setVelocity(courier.getVelocity());
        courierDTO.setLongitude(courier.getLongitude());
        courierDTO.setLatitude(courier.getLatitude());
        courierDTO.setDateTime(courier.getDateTime());

        Message<CourierDTO> message = MessageBuilder
                .withPayload(courierDTO)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
        log.info("KAFKA - SEND: " + message.toString());
    }

}
