package com.cyecize.skatefixers.areas.products.viewModels;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.entities.products.Product;

import java.util.List;

public class ProductDetailsViewModel {

    private BaseProduct product;

    private List<Category> categories;

    private List<BaseProduct> similarProducts;

    public ProductDetailsViewModel(BaseProduct product, List<Category> categories, List<BaseProduct> similarProducts) {
        this.product = product;
        this.categories = categories;
        this.similarProducts = similarProducts;
    }

    public BaseProduct getProduct() {
        return product;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<BaseProduct> getSimilarProducts() {
        return similarProducts;
    }
}
