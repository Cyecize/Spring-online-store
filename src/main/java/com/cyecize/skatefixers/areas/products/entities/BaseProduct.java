package com.cyecize.skatefixers.areas.products.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance
@Table(name = "products")
public abstract class BaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "weekly_views")
    private Integer weeklyViews;

    @ManyToOne(targetEntity = Brand.class)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @OneToMany(targetEntity = Image.class, mappedBy = "product")
    private List<Image> gallery;


    protected BaseProduct(){
        this.gallery = new ArrayList<>();
        this.isEnabled = true;
        this.weeklyViews = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Integer getWeeklyViews() {
        return weeklyViews;
    }

    public void setWeeklyViews(Integer weeklyViews) {
        this.weeklyViews = weeklyViews;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getGallery() {
        return gallery;
    }

    public void setGallery(List<Image> gallery) {
        this.gallery = gallery;
    }
}
