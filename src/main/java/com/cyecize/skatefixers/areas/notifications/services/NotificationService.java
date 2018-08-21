package com.cyecize.skatefixers.areas.notifications.services;

import com.cyecize.skatefixers.areas.notifications.models.Notification;
import com.cyecize.skatefixers.areas.users.entities.User;


import java.util.List;

public interface NotificationService {

    void sendNotification(User user, String content, String href);

    void seeNotification(Notification notification);

    void remove(Notification notification);

    void remove(List<Notification> notifications);

    Notification findOneById(Long id);

    List<Notification> findByUser(User user);

    List<Notification> findNotSeenByUser(User user);
}
