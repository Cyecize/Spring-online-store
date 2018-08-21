package com.cyecize.skatefixers.areas.orders.services;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.enums.OrderStatus;
import com.cyecize.skatefixers.areas.orders.repositories.OrderRepository;
import com.cyecize.skatefixers.areas.orders.viewModels.WorkerOrderViewModel;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.exceptions.InternalException;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

@Service
public class OrderServiceImpl implements OrderService {

    private class Eligibility {
        public boolean isEligible;

        public List<ShoppingCartItem> shoppingCart;

        public Eligibility(boolean isEligible, List<ShoppingCartItem> shoppingCart) {
            this.isEligible = isEligible;
            this.shoppingCart = shoppingCart;
        }
    }

    private final OrderRepository orderRepository;

    private final ShoppingCartService shoppingCartService;

    private final BaseProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartService shoppingCartService, BaseProductService productService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }


    @Override
    public void acceptOrder(Order order) {
        Eligibility eligibility = this.isOrderEligible(order);
        if(order.getOrderStatus() != OrderStatus.AWAITING || !eligibility.isEligible)
            throw new InternalException("Order is not eligible to be accepted!");
        for (ShoppingCartItem item : eligibility.shoppingCart) {
            BaseProduct product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
            this.productService.save(product);
        }
        order.setOrderStatus(OrderStatus.ACCEPTED);
        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public void rejectOrder(Order order) {
        if(order.getOrderStatus() != OrderStatus.AWAITING)
            throw new InternalException("Order has already been edited!");
        order.setOrderStatus(OrderStatus.REJECTED);
        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public Order createOrder(User user, Address address) {
        List<ShoppingCartItem> shoppingCart = this.shoppingCartService.getShoppingCart();
        if (shoppingCart.isEmpty())
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
    public Order findById(Long id) {
        if (!this.orderRepository.findById(id).isPresent())
            throw new NotFoundException("Order does not exist");
        return this.orderRepository.findById(id).get();
    }

    @Override
    public WorkerOrderViewModel forgeWorkerOrderViewModel(Order order) {
        Eligibility eligibility = this.isOrderEligible(order);
        return new WorkerOrderViewModel(order, eligibility.shoppingCart, eligibility.isEligible);
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
            workerOrderViewModels.add(new WorkerOrderViewModel(order, new ArrayList<>(), false));
        }
        return workerOrderViewModels;
    }

    private Eligibility isOrderEligible(Order order) {
        List<ShoppingCartItem> shoppingCartItems = null;
        try {
            shoppingCartItems = this.shoppingCartService.parseShoppingCart(order.getSerializedProducts());
        } catch (Exception e) {
            return new Eligibility(false, new ArrayList<>());
        }
        boolean isEligible = true;
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            if (shoppingCartItem.getQuantity() > shoppingCartItem.getProduct().getQuantity()) {
                isEligible = false;
                break;
            }
        }
        return new Eligibility(isEligible, shoppingCartItems);
    }
}
