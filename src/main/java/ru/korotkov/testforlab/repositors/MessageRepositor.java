package ru.korotkov.testforlab.repositors;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korotkov.testforlab.model.MessageForUser;

import java.util.List;

/**
 * @author Korotkov Sergey
 */

public interface MessageRepositor extends JpaRepository<MessageForUser, Long> {
   MessageForUser findByCorrelationId(String correlationId);
   List<MessageForUser> findByResultCheckIsNotNullAndIsSendMail(Boolean isSendMail);
}
