package com.cyecize.skatefixers.areas.products.entities;


import com.cyecize.skatefixers.areas.products.eventListeners.CategoryListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "product_categories")
@EntityListeners(CategoryListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_category", nullable = true)
    private Category parentCategory;

    @Column(name = "category_name_latin", nullable = false, unique = true)
    private String categoryNameLatin;

    @Column(name = "category_name_cyrillic", nullable = false, unique = true)
    private String categoryNameCyrillic;

    @OneToMany(targetEntity = Category.class, mappedBy = "parentCategory", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Category> subCategories;

    @OneToMany(targetEntity = BaseProduct.class, mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<BaseProduct> products;

    public Category(){
        this.subCategories = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getCategoryNameLatin() {
        return categoryNameLatin;
    }

    public void setCategoryNameLatin(String categoryNameLatin) {
        this.categoryNameLatin = categoryNameLatin;
    }

    public String getCategoryNameCyrillic() {
        return categoryNameCyrillic;
    }

    public void setCategoryNameCyrillic(String categoryNameCyrillic) {
        this.categoryNameCyrillic = categoryNameCyrillic;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }

    public List<BaseProduct> activeProducts(){
        return this.products.stream().filter(BaseProduct::isEnabled).collect(Collectors.toList());
    }

    public List<BaseProduct> activeProductsRecursive(){
        List<BaseProduct> products = this.activeProducts().stream().filter(BaseProduct::isEnabled).collect(Collectors.toList());
        for(Category c : this.subCategories)
            products.addAll(c.activeProductsRecursive());
        return products;
    }
}
