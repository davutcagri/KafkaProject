package davutcagri.KafkaProducer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@RedisHash("courier-dao")
public class Courier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double latitude;
    private double longitude;
    private LocalDateTime dateTime;
    private double velocity;

}
