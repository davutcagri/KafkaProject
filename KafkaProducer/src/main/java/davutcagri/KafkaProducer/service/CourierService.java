package davutcagri.KafkaProducer.service;

import davutcagri.KafkaProducer.entity.Courier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CourierService {

    @Value("${courier.id}")
    private Long courierId;

    @Value(("${latitude}"))
    private Double latitude;

    @Value("${longitude}")
    private Double longitude;

    @Value("${velocity}")
    private Double velocity;

    public Courier createCouirer() {
        Courier courier = new Courier();
        courier.setId(courierId);
        courier.setLatitude(latitude);
        courier.setLongitude(longitude);
        courier.setVelocity(velocity);
        courier.setDateTime(LocalDateTime.now());
        return courier;
    }

    public void moveForward() {
        Random random = new Random();
        double randomAdditionLat = random.nextDouble(0.001, 0.003);
        double randomAdditionLong = random.nextDouble(0.002, 0.004);
       latitude += randomAdditionLat;
       longitude += randomAdditionLong;
    }

}
