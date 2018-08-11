package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> findMainCategories() {
        return this.repository.findMainCategories();
    }

    @Override
    public List<Category> findAllParentCategories(Category category) {
        List<Category> categories = new ArrayList<>();
        if(category.getParentCategory() != null)
            categories.addAll(this.findAllParentCategories((category.getParentCategory())));
        categories.add(category);
        return categories;
    }


    @Override
    public Category findOneByName(String name) {
        //todo throw exception
        return this.repository.findCategoryByCategoryNameLatinOrCategoryNameCyrillic(name, name);
    }
}
