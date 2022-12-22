package com.mj.email.service;

public interface EmailSender {

    void send(String address, String subject, String text);
}
