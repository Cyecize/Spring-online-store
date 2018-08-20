package com.cyecize.skatefixers.areas.orders.entities;

import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.entities.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "serialized_products", nullable = false)
    private String serializedProducts;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "is_accepted")
    private Boolean accepted;

    private LocalDateTime orderDate;

    public Order(){
        this.orderDate  = LocalDateTime.now();
        this.totalPrice = 0D;
        this.accepted = false;
    }

    @PrePersist
    public void onPersist(){
        this.orderDate = LocalDateTime.now();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
