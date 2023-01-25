package uol.compass.msorder.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.orders}")
    private String queue;

    @Bean
    public Queue queuePublishOrder(){
        return new Queue(queue, true);
    }


}
