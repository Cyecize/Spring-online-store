package com.cyecize.skatefixers.areas.shoppingCart.entities;

import com.cyecize.skatefixers.areas.users.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    @Column(name = "serialized_products")
    private String serializedProducts;

    public ShoppingCart(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSerializedProducts() {
        return serializedProducts;
    }

    public void setSerializedProducts(String serializedProducts) {
        this.serializedProducts = serializedProducts;
    }
}
