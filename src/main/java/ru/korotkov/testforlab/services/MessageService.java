package ru.korotkov.testforlab.services;

import ru.korotkov.testforlab.model.MessageForUser;

import java.util.List;

/**
 * @author Korotkov Sergey
 */
public interface MessageService {
    void addMessage(MessageForUser message);

    void delete(Long id);

    void deleteAll();

    List<MessageForUser> getALL();

    MessageForUser getMessage(Long id);

    MessageForUser getMessageCorrelationId(String correlationId);

    List<MessageForUser> getListMessageNotMail(Boolean isSendMail);
}
