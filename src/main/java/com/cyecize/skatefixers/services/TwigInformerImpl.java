package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TwigInformerImpl implements TwigInformer {

    private final CategoryService categoryService;

    private User principal;

    @Autowired
    public TwigInformerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Category> getCategoriesToDisplay() {
        return this.categoryService.findMainCategories();
    }

    @Override
    public User getUser() {
        return this.principal;
    }

    @Override
    public boolean hasRole(String role) {
        if(this.principal == null) return false;
        return this.principal.getRoles().stream().filter(r -> r.getAuthority().equals(role)).findFirst().orElse(null) != null;
    }

    @Override
    public void setUser(User principal) {
        this.principal = principal;
    }
}
