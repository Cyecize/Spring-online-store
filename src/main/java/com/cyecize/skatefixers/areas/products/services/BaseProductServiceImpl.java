package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.repositories.BaseProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseProductServiceImpl implements BaseProductService{

    private final BaseProductRepository productRepository;

    @Autowired
    public BaseProductServiceImpl(BaseProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BaseProduct findOneById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public List<BaseProduct> findAll() {
        return this.productRepository.findBaseProductsByIsEnabled(true);
    }

    @Override
    public List<BaseProduct> findAllIncludeDisabled() {
        return productRepository.findAll();
    }

    @Override
    public List<BaseProduct> findTrendyProducts() {
        return productRepository.findTop6ByOrderByWeeklyViewsDesc();
    }

    @Override
    public List<BaseProduct> findNewProducts(int limit) {
        return productRepository.findNewProductsLimit(limit);
    }
}
