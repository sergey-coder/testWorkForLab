package ru.korotkov.testforlab.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korotkov.testforlab.model.MessageForUser;
import ru.korotkov.testforlab.repositors.MessageRepositor;
import ru.korotkov.testforlab.services.MessageService;

import java.util.List;

/**
 * @author Korotkov Sergey
 */

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepositor messageRepositor;

    @Autowired
    public MessageServiceImpl(MessageRepositor messageRepositor) {
        this.messageRepositor = messageRepositor;
    }

    @Override
    public void addMessage(MessageForUser message) {
        messageRepositor.saveAndFlush(message);
    }

    @Override
    public void delete(Long id) {
        messageRepositor.deleteById(id);
    }

    @Override
    public void deleteAll() {
        messageRepositor.deleteAll();
    }

    @Override
    public List<MessageForUser> getALL() {
        return messageRepositor.findAll();
    }

    @Override
    public MessageForUser getMessage(Long id) {
        return messageRepositor.getOne(id);
    }

    public MessageForUser getMessageCorrelationId(String correlationId) {
        return messageRepositor.findByCorrelationId(correlationId);
    }

    @Override
    public List<MessageForUser> getListMessageNotMail(Boolean isSendMail) {
        return messageRepositor.findByResultCheckIsNotNullAndIsSendMail(isSendMail);
    }

}
