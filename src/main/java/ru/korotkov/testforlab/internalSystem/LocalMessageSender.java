package ru.korotkov.testforlab.internalSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.korotkov.testforlab.config.MessagingApplication;

/**
 * @author Korotkov Sergey
 */

//наш отсылатель сообщений на внешную систему
@Service
public class LocalMessageSender {

    private static final Logger log = LoggerFactory.getLogger(LocalMessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    public LocalMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendMessage(String message) {
        CorrelationData correlationData = new CorrelationData();
        rabbitTemplate.convertAndSend(MessagingApplication.EXCHANGE_NAME, MessagingApplication.ROUTING_KEY, message,correlationData);
        return correlationData.toString();
    }


}
