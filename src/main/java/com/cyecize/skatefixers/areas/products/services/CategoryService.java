package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.bindingModels.CategoryBindingModel;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Category;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryBindingModel bindingModel);

    void editCategory(String categoryName,CategoryBindingModel bindingModel);

    void removeCategory(Long id);

    List<Category> findMainCategories();

    List<Category> findAllParentCategories(Category category);

    Category findOneByName(String name);

    List<Category> findAll();

}
