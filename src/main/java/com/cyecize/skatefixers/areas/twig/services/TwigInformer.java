package com.cyecize.skatefixers.areas.twig.services;

import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.users.entities.User;

import java.util.List;

public interface TwigInformer {

    List<Category> getCategoriesToDisplay();

    User getUser();

    void setUser(User principal);

    boolean hasRole(String role);

}
