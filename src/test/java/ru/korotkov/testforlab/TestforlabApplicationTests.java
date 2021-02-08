package ru.korotkov.testforlab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.korotkov.testforlab.model.Role;
import ru.korotkov.testforlab.model.Status;
import ru.korotkov.testforlab.model.User;
import ru.korotkov.testforlab.internalSystem.LocalMessageSender;
import ru.korotkov.testforlab.services.impl.CheckingUsersManagerImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TestforlabApplicationTests {
    @Autowired
    private CheckingUsersManagerImpl checkingUsersManager;

    @Autowired
    private LocalMessageSender localMessageSender;

    @Test
    void contextLoads() {
    }

    @Test
    void TestJson() {
        String userAsString = "";
        try {
            userAsString = (new ObjectMapper()).writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        localMessageSender.sendMessage(userAsString);
    }

   /* @Test
    void TestSendMessegeInQueue() {
        String userAsString="";
        String routingKey="test.lab.external.system";
        try {
            userAsString = (new ObjectMapper()).writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        runner.run(userAsString);
    }*/

    @Test
    void TestSendMail() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class[] paramTypes = new Class[]{String.class, String.class};
        Method method = checkingUsersManager.getClass().getDeclaredMethod("sendMail", paramTypes);
        method.setAccessible(true);
        method.invoke(checkingUsersManager, "sergekorotko@yandex.ru", "messageBody");
    }

    @Test
    void simplJsonTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = checkingUsersManager.getClass().getDeclaredMethod("sendMassegeForRabbit");
        method.setAccessible(true);
        Class[] paramTypes = new Class[]{User.class, String.class};
        Method concat = checkingUsersManager.getClass().getMethod("concat", paramTypes);
        concat.invoke(checkingUsersManager, user, "routingKey");
    }

    {
        Map<String, String> userData = new HashMap<>();
        userData.put("login", "loginObject");
        userData.put("password", "passwordObject");
        userData.put("email", "emailObject");
        userData.put("username", "usernameObject");
        userData.put("userpatronymic", "userpatronymicObject");
        userData.put("userfamily", "userfamilyObject");
    }

    User user = new User("login",
            "password",
            "email",
            "username",
            "userpatronymic",
            "userfamily",
            Role.USER,
            Status.ACTIVE);

}
