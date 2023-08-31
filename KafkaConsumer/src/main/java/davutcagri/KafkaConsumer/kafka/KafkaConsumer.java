package davutcagri.KafkaConsumer.kafka;

import davutcagri.KafkaConsumer.entity.Courier;
import davutcagri.KafkaConsumer.repository.CourierRepository;
import davutcagri.KafkaProducer.dto.CourierDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Slf4j
public class KafkaConsumer {

    private final RedisTemplate<String, String> redisTemplate;
    private final CourierRepository courierRepository;

    public KafkaConsumer(RedisTemplate<String, String> redisTemplate, CourierRepository courierRepository) {
        this.redisTemplate = redisTemplate;
        this.courierRepository = courierRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeData(CourierDTO courierDTO) {
        log.info("KAFKA - GET: " + courierDTO);

        Courier courier = new Courier();
        courier.setLatitude(courierDTO.getLatitude());
        courier.setLongitude(courierDTO.getLongitude());
        courier.setVelocity(courierDTO.getVelocity());
        courier.setDateTime(courierDTO.getDateTime());
        //courierRepository.save(courier);
        redisTemplate.opsForZSet().add("Courier|Data", courier.toString(), (double) Timestamp.valueOf(courier.getDateTime()).getTime());
    }

}