package com.cyecize.skatefixers.areas.products.viewModels;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public class CategoriesPageViewModel {

    private List<Category> subCategories;

    private Page<BaseProduct> products;

    private Category category;

    public CategoriesPageViewModel(List<Category> subCategories, Page<BaseProduct> products) {
        this.subCategories = subCategories;
        this.products = products;
    }

    public CategoriesPageViewModel(List<Category> subCategories, Page<BaseProduct> products, Category category){
        this(subCategories, products);
        this.category = category;
    }

    public List<Category> getSubCategories() {
        return this.subCategories;
    }

    public Page<BaseProduct> getProducts() {
        return this.products;
    }

    public Category getCategory() {
        return this.category;
    }
}
