package com.cyecize.skatefixers.areas.shoppingCart.viewModels;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;

public class ShoppingCartItem {

    private BaseProduct product;

    private Integer quantity;

    public ShoppingCartItem(BaseProduct product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BaseProduct getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
