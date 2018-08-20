package com.cyecize.skatefixers.areas.orders.services;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.enums.OrderStatus;
import com.cyecize.skatefixers.areas.orders.repositories.OrderRepository;
import com.cyecize.skatefixers.areas.orders.viewModels.WorkerOrderViewModel;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.exceptions.JsonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.DoubleAdder;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order createOrder(User user, Address address ) {
        List<ShoppingCartItem> shoppingCart = this.shoppingCartService.getShoppingCart();
        if(shoppingCart.isEmpty())
            throw new JsonException("Shopping cart is empty!");
        Order order = new Order();
        order.setAddress(address);
        order.setSerializedProducts(this.shoppingCartService.encodeShoppingCart(shoppingCart));
        order.setUser(user);
        DoubleAdder da = new DoubleAdder();
        shoppingCart.forEach(sc -> da.add(sc.getQuantity() * sc.getProduct().getPrice()));
        order.setTotalPrice(da.doubleValue());
        this.orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return this.orderRepository.findByOrderStatus(status);
    }

    @Override
    public List<Order> findOrders(User user) {
        return this.orderRepository.findByUser(user);
    }

    @Override
    public List<WorkerOrderViewModel> forgeWorkerOrdersViewModel(List<Order> orders) {
        List<WorkerOrderViewModel> workerOrderViewModels = new ArrayList<>();
        for (Order order : orders) {
            workerOrderViewModels.add(new WorkerOrderViewModel(order, new ArrayList<>(),false));
        }
        return workerOrderViewModels;
    }
}
