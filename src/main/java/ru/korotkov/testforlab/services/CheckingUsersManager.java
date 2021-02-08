package ru.korotkov.testforlab.services;

public interface CheckingUsersManager {

    void сheckingUsers(String login,
                       String password,
                       String email,
                       String username,
                       String userpatronymic,
                       String userfamily);

}
