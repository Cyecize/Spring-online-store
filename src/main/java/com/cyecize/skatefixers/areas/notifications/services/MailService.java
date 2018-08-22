package com.cyecize.skatefixers.areas.notifications.services;

import com.cyecize.skatefixers.areas.users.entities.User;

import java.util.List;

public interface MailService {

    void sendMessageToUser(User user, String subject, String message);

    void sendMessageToEmail(String email, String subject, String message);

    void sendMessageToUsers(List<User> byRole, String subject, String message);
}
