package com.cyecize.skatefixers.areas.orders.viewModels;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.enums.OrderStatus;
import com.cyecize.skatefixers.areas.products.entities.products.Product;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;

import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

public class WorkerOrderViewModel {

    private Order order;

    private List<ShoppingCartItem> shoppingCart;

    private boolean isExecutable;

    public WorkerOrderViewModel(Order order, List<ShoppingCartItem> shoppingCart ,boolean isExecutable) {
        this.order = order;
        this.isExecutable = isExecutable;
        this.shoppingCart = shoppingCart;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isExecutable() {
        return isExecutable;
    }

    public double calcTotal(){
        DoubleAdder da = new DoubleAdder();
        this.shoppingCart.forEach(ci -> da.add(ci.getQuantity() * ci.getProduct().getPrice()));
        return da.doubleValue();
    }

    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }

    public boolean isAccepted(){
        return this.order.getOrderStatus() == OrderStatus.ACCEPTED;
    }
    public boolean isRejected(){
        return this.order.getOrderStatus() == OrderStatus.REJECTED;
    }
    public boolean isAwaiting(){
        return this.order.getOrderStatus() == OrderStatus.AWAITING;
    }
}
