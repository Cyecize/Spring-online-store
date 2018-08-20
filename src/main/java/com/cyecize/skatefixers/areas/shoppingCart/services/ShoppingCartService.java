package com.cyecize.skatefixers.areas.shoppingCart.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import com.cyecize.skatefixers.areas.users.entities.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ShoppingCartService {

    void clear();

    void mergeDbAndCookieCarts(User user);

    void addProduct(BaseProduct product, int quantity);

    void initCart(String cookie);

    void saveCart(HttpServletResponse response);

    void removeProduct(Long productId);

    int getShoppingCartSize();

    List<ShoppingCartItem> getShoppingCart();

}