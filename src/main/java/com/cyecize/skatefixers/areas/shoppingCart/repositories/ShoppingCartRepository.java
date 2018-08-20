package com.cyecize.skatefixers.areas.shoppingCart.repositories;

import com.cyecize.skatefixers.areas.shoppingCart.entities.ShoppingCart;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartByUser(User user);
}
