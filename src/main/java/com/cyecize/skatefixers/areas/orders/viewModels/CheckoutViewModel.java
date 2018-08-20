package com.cyecize.skatefixers.areas.orders.viewModels;

import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import com.cyecize.skatefixers.areas.users.entities.Address;

import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

public class CheckoutViewModel {

    private List<ShoppingCartItem> shoppingCart;

    private List<Address> addresses;

    private double total;

    public CheckoutViewModel(List<ShoppingCartItem> shoppingCart, List<Address> addresses) {
        this.shoppingCart = shoppingCart;
        this.addresses = addresses;
        DoubleAdder doubleAdder = new DoubleAdder();
        shoppingCart.forEach(sc -> doubleAdder.add(sc.getQuantity() * sc.getProduct().getPrice()));
        this.total = doubleAdder.doubleValue();
    }

    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }

    public double getTotal() {
        return total;
    }

    public boolean isCartEmpty(){
        return this.shoppingCart.isEmpty();
    }

    public boolean isAddressesEmpty(){
        return this.addresses.isEmpty();
    }
}
