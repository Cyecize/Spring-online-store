package com.cyecize.skatefixers.areas.products.viewModels;

import com.cyecize.skatefixers.areas.products.bindingModels.CreateProductBindingModel;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.entities.products.Product;

import java.util.List;

public class CreateEditProductViewModel {


    private List<Category> categories;

    private List<Brand> brands;

    public CreateEditProductViewModel(List<Category> categories, List<Brand> brands) {
        this.categories = categories;
        this.brands = brands;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
