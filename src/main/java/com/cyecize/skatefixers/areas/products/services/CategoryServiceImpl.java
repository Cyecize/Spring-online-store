package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.repositories.CategoryRepository;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final LocalLanguage localLanguage;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, LocalLanguage localLanguage) {
        this.repository = repository;
        this.localLanguage = localLanguage;
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
        Category category = this.repository.findCategoryByCategoryNameLatinOrCategoryNameCyrillic(name, name);
        if(category == null)
            throw new NotFoundException(this.localLanguage.errors().categoryWithNameWasNotFound(name));
        return category;
    }
}
