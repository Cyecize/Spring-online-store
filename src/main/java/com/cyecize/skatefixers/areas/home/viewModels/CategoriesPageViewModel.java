package com.cyecize.skatefixers.areas.home.viewModels;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public class CategoriesPageViewModel {

    private List<Category> subCategories;

    private Page<BaseProduct> products;

    public CategoriesPageViewModel(List<Category> subCategories, Page<BaseProduct> products) {
        this.subCategories = subCategories;
        this.products = products;

    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public Page<BaseProduct> getProducts() {
        return products;
    }

}
