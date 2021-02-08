package ru.korotkov.testforlab.externalSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korotkov.testforlab.config.MessagingApplication;
import ru.korotkov.testforlab.internalSystem.LocalMessageListener;

import java.nio.charset.StandardCharsets;

/**
 * @author Korotkov Sergey
 */

@Service
public class RabbirListenerMessage {
    private static final Logger log = LoggerFactory.getLogger(LocalMessageListener.class);

    RabbitSendMessage rabbitSendMessage;

    @Autowired
    public RabbirListenerMessage(RabbitSendMessage rabbitSendMessage) {
        this.rabbitSendMessage = rabbitSendMessage;
    }

    // слушаем очередь TestQueueCheckUser
    //когда приходит сообщение в ответ отсылаем рандомный ответ

    @RabbitListener(queues = MessagingApplication.QUEUE_INPUT)
    public void receiveMessage(final Message message) {
        String messageCheck =  new String(message.getBody(), StandardCharsets.UTF_8);
        rabbitSendMessage.sendMessage();
    }

}


