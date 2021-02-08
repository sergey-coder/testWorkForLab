package ru.korotkov.testforlab.services;

import ru.korotkov.testforlab.model.User;

import java.util.List;

/**
 * @author Korotkov Sergey
 */

public interface UserService {
    void addUser(User user);

    void delete(Long id);

    void deleteAll();

    List<User> getALL();

    User getUser(Long id);
}
