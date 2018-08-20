package com.cyecize.skatefixers.areas.twig.services;

import com.cyecize.skatefixers.areas.home.entities.Banner;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TwigInformer {

    void setUser(User principal);


    boolean hasRole(String role);

    Banner getBanner();

    User getUser();

    List<Category> getCategoriesToDisplay();

    List<BaseProduct> getNewProducts();

    List<BaseProduct> getNewProducts(int limit);

    List<Brand> getAllBrands();




}
