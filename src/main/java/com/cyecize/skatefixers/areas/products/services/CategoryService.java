package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findMainCategories();

    Category findOneByName(String name);
}
