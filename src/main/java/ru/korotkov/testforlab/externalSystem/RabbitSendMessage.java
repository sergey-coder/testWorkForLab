package ru.korotkov.testforlab.externalSystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.korotkov.testforlab.config.MessagingApplication;
import ru.korotkov.testforlab.model.User;

import java.util.Random;

/**
 * @author Korotkov Sergey
 */

@Service
public class RabbitSendMessage {

    private final RabbitTemplate rabbitTemplate;

    private Boolean message;

    public RabbitSendMessage(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage() {
        int random = new Random().nextInt(2);
        switch (random) {
            case 0:
                message = false;
                break;
            case 1:
                message = true;
                break;
            default:
                System.out.println("ошибка при формировании сообщения");
                break;
        }
        rabbitTemplate.convertAndSend(MessagingApplication.EXCHANGE_NAME, MessagingApplication.ROUTING_KEY, createMessageJSON(message));
    }

    private String createMessageJSON(Boolean message) {
        String userAsString = "";
        try {
            userAsString = (new ObjectMapper()).writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userAsString;
    }
}
