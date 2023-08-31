package davutcagri.KafkaProducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@RedisHash("courier-dto")
public class CourierDTO implements Serializable {

    private Long courierId;
    private double latitude;
    private double longitude;
    private LocalDateTime dateTime;
    private double velocity;

}
