package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwigInformerImpl implements TwigInformer {

    private final CategoryService categoryService;

    @Autowired
    public TwigInformerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Category> getCategoriesToDisplay() {
        return this.categoryService.findMainCategories();
    }

    @Override
    public boolean hasRole(String role) {
        return role.equals("ADMIN");
    }

    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}
