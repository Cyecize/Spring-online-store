package com.cyecize.skatefixers.areas.home.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchService {

    String searchNonProducts(String text);

    Page<BaseProduct> searchProducts(String text, Pageable pageable);

    Page<BaseProduct> searchAllProducts(String text, Pageable pageable);

}
