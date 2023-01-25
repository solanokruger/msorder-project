package uol.compass.mshistory.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uol.compass.mshistory.models.entities.OrderPublisherData;
import uol.compass.mshistory.services.HistoryServiceImpl;

@Component
@RequiredArgsConstructor
public class OrderSubscriber {
    ObjectMapper mapper = new ObjectMapper();

    private final HistoryServiceImpl historyService;

    @RabbitListener(queues = "${mq.queues.orders}")
    public void orderReceiver(@Payload String payload) throws JsonProcessingException {
            OrderPublisherData orderData = mapper.readValue(payload, OrderPublisherData.class);
            historyService.create(orderData);
    }

}
