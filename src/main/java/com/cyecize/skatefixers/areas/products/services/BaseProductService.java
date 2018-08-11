package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseProductService {

    void viewProduct(Long productId);

    BaseProduct findOneById(Long id);

    Page<BaseProduct> findAll(Pageable pageable);

    List<BaseProduct> findSimilarProducts(BaseProduct product, int limit );

    List<BaseProduct> findAllIncludeDisabled();

    List<BaseProduct> findTrendyProducts();

    List<BaseProduct> findNewProducts(int limit);
}
