package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Category findOneByName(String name) {
        //todo throw exception
        return this.repository.findCategoryByCategoryNameLatinOrCategoryNameCyrillic(name, name);
    }
}
