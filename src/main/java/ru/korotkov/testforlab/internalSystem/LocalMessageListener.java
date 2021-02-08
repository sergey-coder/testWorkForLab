package ru.korotkov.testforlab.internalSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.korotkov.testforlab.config.MessagingApplication;
import ru.korotkov.testforlab.services.impl.CheckingUsersManagerImpl;

/**
 * @author Korotkov Sergey
 */
@Service
public class LocalMessageListener {

    private static final Logger log = LoggerFactory.getLogger(LocalMessageListener.class);

    private CheckingUsersManagerImpl checkingUsersManager;

    public LocalMessageListener(CheckingUsersManagerImpl checkingUsersManager) {
        this.checkingUsersManager = checkingUsersManager;
    }

    @RabbitListener(queues = MessagingApplication.QUEUE_INPUT)
    public void receiveMessage(final Message message) {
        checkingUsersManager.setInputMessage(message);
    }

}