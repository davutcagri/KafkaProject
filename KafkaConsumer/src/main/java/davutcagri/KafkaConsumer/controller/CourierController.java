package davutcagri.KafkaConsumer.controller;

import davutcagri.KafkaConsumer.entity.Courier;
import davutcagri.KafkaConsumer.service.CourierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/courier")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping("/get")
    public Courier getCourier() {
        return courierService.getCourier();
    }
}
