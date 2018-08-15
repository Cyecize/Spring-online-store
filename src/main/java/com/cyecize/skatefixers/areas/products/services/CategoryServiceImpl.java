package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.CategoryBindingModel;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.repositories.CategoryRepository;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final LocalLanguage localLanguage;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, LocalLanguage localLanguage, ModelMapper modelMapper) {
        this.repository = repository;
        this.localLanguage = localLanguage;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCategory(CategoryBindingModel bindingModel) {
        Category parent = null;
        if(bindingModel.getParentCategoryId() != null && bindingModel.getParentCategoryId() > 0){
            if(!this.repository.findById(bindingModel.getParentCategoryId()).isPresent())
                throw new NotFoundException(this.localLanguage.errors().categoryWasNotFound());
            parent = this.repository.findById(bindingModel.getParentCategoryId()).get();
        }
        Category category = this.modelMapper.map(bindingModel, Category.class);
        category.setId(null);
        category.setParentCategory(parent);
        this.repository.saveAndFlush(category);
    }

    @Override
    public void editCategory(String categoryName, CategoryBindingModel bindingModel) {
        Category currentCategory = this.findOneByName(categoryName);
        Category parent = null;
        if(bindingModel.getParentCategoryId() != null && bindingModel.getParentCategoryId() > 0){
            if(!this.repository.findById(bindingModel.getParentCategoryId()).isPresent())
                throw new NotFoundException(this.localLanguage.errors().categoryWasNotFound());
            parent = this.repository.findById(bindingModel.getParentCategoryId()).get();
        }
        currentCategory.setCategoryNameCyrillic(bindingModel.getCategoryNameCyrillic());
        currentCategory.setCategoryNameLatin(bindingModel.getCategoryNameLatin());
        currentCategory.setParentCategory(parent);
        this.repository.saveAndFlush(currentCategory);
    }

    @Override
    public void removeCategory(Long id) {
        if(!this.repository.findById(id).isPresent())
            throw new JsonException("Category was not found!");
        this.repository.delete(this.repository.findById(id).get());
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

    @Override
    public List<Category> findAll() {
        return this.repository.findAll();
    }
}
