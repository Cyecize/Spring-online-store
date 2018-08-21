package com.cyecize.skatefixers.areas.notifications.services;

import com.cyecize.skatefixers.areas.notifications.models.Notification;
import com.cyecize.skatefixers.areas.notifications.repositories.NotificationRepository;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendNotification(User user, String content, String href) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setContent(content);
        notification.setHref(href);
        this.notificationRepository.saveAndFlush(notification);
    }

    @Override
    public void seeNotification(Notification notification) {
        notification.setSeen(true);
        this.notificationRepository.saveAndFlush(notification);
    }

    @Override
    public void remove(Notification notification) {
        this.notificationRepository.delete(notification);
    }

    @Override
    public void remove(List<Notification> notifications) {
        for (Notification notification : notifications) {
            this.notificationRepository.deleteById(notification.getId());
        }
    }

    @Override
    public Notification findOneById(Long id) {
        if(!this.notificationRepository.findById(id).isPresent())
            throw new NotFoundException("notification not found");
        return this.notificationRepository.findById(id).get();
    }

    @Override
    public List<Notification> findByUser(User user) {
        return this.notificationRepository.findByUserOrderByIdDesc(user);
    }

    @Override
    public List<Notification> findNotSeenByUser(User user) {
        return this.notificationRepository.findByUserAndSeenFalseOrderByIdDesc(user);
    }
}
