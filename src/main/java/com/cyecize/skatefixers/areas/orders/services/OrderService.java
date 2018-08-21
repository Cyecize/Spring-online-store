package com.cyecize.skatefixers.areas.orders.services;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.enums.OrderStatus;
import com.cyecize.skatefixers.areas.orders.viewModels.WorkerOrderViewModel;
import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.entities.User;

import java.util.List;

public interface OrderService {

    void acceptOrder(Order order);

    void rejectOrder(Order order);

    Order createOrder(User user, Address address);

    Order findById(Long id);

    WorkerOrderViewModel forgeWorkerOrderViewModel(Order order);

    List<Order> findAll();

    List<Order> findByStatus(OrderStatus status);

    List<Order> findOrders(User user);

    List<WorkerOrderViewModel> forgeWorkerOrdersViewModel(List<Order> orders);

}
