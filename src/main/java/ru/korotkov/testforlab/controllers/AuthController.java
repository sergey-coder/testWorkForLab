package ru.korotkov.testforlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.korotkov.testforlab.services.CheckingUsersManager;

@Controller

public class AuthController {

    CheckingUsersManager checkingUsersManager;

    @Autowired
    public AuthController(CheckingUsersManager checkingUsersManager) {
        this.checkingUsersManager = checkingUsersManager;
    }

    @RequestMapping("/")
    public String getLoginPages() {
        return "/login";
    }

    @RequestMapping("/auth/login")
    public String getLoginPage() {
        return "/login";
    }

    @GetMapping("/auth/success")
    public String getSuccessPage() {
        return "/success";
    }

    @GetMapping("/auth/registration")
    public String getRegistration() {
        return "/registration";
    }

    @PostMapping("/auth/registration")
    public String getRegistrationData(@RequestParam("login") String login,
                                      @RequestParam("password") String password,
                                      @RequestParam("email") String email,
                                      @RequestParam("username") String username,
                                      @RequestParam("userpatronymic") String userpatronymic,
                                      @RequestParam("userfamily") String userfamily,
                                      Model model) {
        checkingUsersManager.сheckingUsers(
                login,
                password,
                email,
                username,
                userpatronymic,
                userfamily);
        return "/login";
    }
}
