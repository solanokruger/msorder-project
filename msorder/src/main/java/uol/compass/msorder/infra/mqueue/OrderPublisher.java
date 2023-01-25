package uol.compass.msorder.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import uol.compass.msorder.model.entities.OrderPublisherData;

@Component
@RequiredArgsConstructor
public class OrderPublisher {

    public static ObjectWriter mapper;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void publishOrder(OrderPublisherData orderData) throws JsonProcessingException {
        var json = mapToJson(orderData);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    static {
        mapper = new ObjectMapper().registerModule(new JavaTimeModule()).writer().withDefaultPrettyPrinter();
    }


}
