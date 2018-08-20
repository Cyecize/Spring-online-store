package com.cyecize.skatefixers.areas.orders.viewModels;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.products.entities.products.Product;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;

import java.util.List;

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

    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }
}
