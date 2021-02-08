package ru.korotkov.testforlab.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.korotkov.testforlab.model.MessageForUser;
import ru.korotkov.testforlab.model.Role;
import ru.korotkov.testforlab.model.Status;
import ru.korotkov.testforlab.model.User;
import ru.korotkov.testforlab.internalSystem.LocalMessageSender;
import ru.korotkov.testforlab.services.CheckingUsersManager;
import ru.korotkov.testforlab.services.MessageService;
import ru.korotkov.testforlab.services.SendMailer;
import ru.korotkov.testforlab.services.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class CheckingUsersManagerImpl implements CheckingUsersManager {

    private UserService userService;
    private SendMailer mailSender;
    private LocalMessageSender localMessageSender;
    private MessageService messageService;

    @Autowired
    public CheckingUsersManagerImpl(MessageService messageService, UserService userService, SendMailer mailSender, LocalMessageSender localMessageSender) {
        this.userService = userService;
        this.mailSender = mailSender;
        this.localMessageSender = localMessageSender;
        this.messageService = messageService;
    }

    @Override
    public void сheckingUsers(String login, String password, String email, String username, String userpatronymic, String userfamily) {

        User user = new User(
                login,
                password,
                email,
                username,
                userpatronymic,
                userfamily,
                Role.USER,
                Status.ACTIVE);

        userService.addUser(user);

        // отправляем данные на сервер
        String correlationId = localMessageSender.sendMessage(createMessageJSON(user));

        //сохраняем данные по message
        MessageForUser message = new MessageForUser();
        message.setUserId(user.getId());
        message.setDataCreate(new Date());
        message.setIsSendMail(false);
        message.setCorrelationId(correlationId);
        messageService.addMessage(message);
    }


    private String createMessageJSON(User user) {
        String userAsString = "";
        try {
            userAsString = (new ObjectMapper()).writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userAsString;
    }

    @Scheduled(fixedDelay = 10000L)
    public void sendResultCheckUser() {

        List<MessageForUser> messageForUserList = messageService.getListMessageNotMail(false);

        for (MessageForUser item : messageForUserList) {
            String resultCheck = item.getResultCheck();
            User user = userService.getUser(item.getUserId());
            String outMessage;

            if (resultCheck.equals("true")) {
                outMessage = "Здравствуйте, " + user.getUsername() + "\n" +
                        "Ваша заявка одобрена" + resultCheck;
            } else {
                outMessage = "Здравствуйте, " + user.getUsername() + "\n" +
                        "Ваша заявка не одобрена";
            }

            item.setIsSendMail(true);
            item.setSendMail(new Date());
            mailSender.sendMail(user.getEmail(), outMessage);
        }
    }

    public void setInputMessage(Message message) {
        String resultCheck = new String(message.getBody(), StandardCharsets.UTF_8);
        String correlationId = message.getMessageProperties().getCorrelationId();
        MessageForUser messageForUser = messageService.getMessageCorrelationId(correlationId);
        if (messageForUser != null) {
            messageForUser.setResultCheck(resultCheck);
        }
    }


}
