package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.products.entities.Category;

import java.util.List;

public interface TwigInformer {

    List<Category> getCategoriesToDisplay();

    boolean hasRole(String role);

    boolean isAuthenticated();
}
