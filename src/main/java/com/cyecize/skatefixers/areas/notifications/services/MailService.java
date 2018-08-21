package com.cyecize.skatefixers.areas.notifications.services;

import com.cyecize.skatefixers.areas.users.entities.User;

public interface MailService {

    void sendMessageToUser(User user, String subject, String message);

    void sendMessageToEmail(String email, String subject, String message);
}
