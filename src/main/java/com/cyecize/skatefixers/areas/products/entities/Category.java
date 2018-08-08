package com.cyecize.skatefixers.areas.products.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "parent_category", nullable = true)
    private Category parentCategory;

    @Column(name = "category_name_latin", nullable = false, unique = true)
    private String categoryNameLatin;

    @Column(name = "category_name_cyrillic", nullable = false, unique = true)
    private String categoryNameCyrillic;

    @OneToMany(targetEntity = Category.class, mappedBy = "parentCategory")
    private List<Category> subCategories;

    @OneToMany(targetEntity = BaseProduct.class, mappedBy = "category")
    private List<BaseProduct> products;

    public Category(){

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
}
