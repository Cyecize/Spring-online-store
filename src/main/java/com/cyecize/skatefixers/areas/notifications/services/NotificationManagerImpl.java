package com.cyecize.skatefixers.areas.notifications.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import com.cyecize.skatefixers.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationManagerImpl implements NotificationManager {


    private final NotificationService notificationService;

    private final UserService userService;

    private final LocalLanguage localLanguage;

    @Autowired
    public NotificationManagerImpl(NotificationService notificationService, UserService userService, LocalLanguage localLanguage) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.localLanguage = localLanguage;
    }

    @Override
    @Async
    public void informWorkersForNewOrder(Order order) {
        List<User> user = this.userService.findByRole(UserRoleType.ROLE_WORKER);
        for (User worker : user) {
            this.notificationService.sendNotification(worker, localLanguage.dictionary().newOrderReceived(), "/orders/review/"+order.getId());
        }
    }

    @Override
    @Async
    public void sendOrderStatusChanged(Order order, User user) {
        this.notificationService.sendNotification(user, this.localLanguage.dictionary().orderStatusChangedNewStatusIs() + order.getOrderStatus().name(), "/users/orders/"+order.getId());
    }

}
