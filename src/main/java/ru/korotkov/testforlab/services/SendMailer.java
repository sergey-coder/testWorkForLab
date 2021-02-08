package ru.korotkov.testforlab.services;

public interface SendMailer {
    void sendMail (String toAddress, String messageBody);
}
