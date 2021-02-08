package ru.korotkov.testforlab.services.impl;

import org.springframework.stereotype.Service;
import ru.korotkov.testforlab.model.User;
import ru.korotkov.testforlab.repositors.UserRepositor;
import ru.korotkov.testforlab.services.UserService;

import java.util.List;
import java.util.Optional;

/**
 * @author Korotkov Sergey
 */

@Service
public class UserServiceImpl implements UserService {

    private UserRepositor userRepositor;

    public UserServiceImpl(UserRepositor userRepositor) {
        this.userRepositor = userRepositor;
    }

    @Override
    public void addUser(User user) {
        userRepositor.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userRepositor.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepositor.deleteAll();
    }

    @Override
    public List<User> getALL() {
        return userRepositor.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepositor.getOne(id);
    }

}


