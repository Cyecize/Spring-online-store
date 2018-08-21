package com.cyecize.skatefixers.areas.notifications.repositories;

import com.cyecize.skatefixers.areas.notifications.models.Notification;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserOrderByIdDesc(User user);

    List<Notification> findByUserAndSeenFalseOrderByIdDesc(User user);
}
