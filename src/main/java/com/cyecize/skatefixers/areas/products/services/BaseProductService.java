package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;

import java.util.List;

public interface BaseProductService {

    BaseProduct findOneById(Long id);

    List<BaseProduct> findAll();

    List<BaseProduct> findAllIncludeDisabled();

    List<BaseProduct> findTrendyProducts();

    List<BaseProduct> findNewProducts(int limit);
}
