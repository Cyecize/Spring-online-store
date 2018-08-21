package com.cyecize.skatefixers.areas.notifications.services;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.users.entities.User;

public interface NotificationManager {

    void informWorkersForNewOrder(Order order);

    void sendOrderStatusChanged(Order order, User user);

}
