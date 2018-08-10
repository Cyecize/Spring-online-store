package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.repositories.BaseProductRepository;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseProductServiceImpl implements BaseProductService{

    private final BaseProductRepository productRepository;

    private final LocalLanguage localLanguage;

    @Autowired
    public BaseProductServiceImpl(BaseProductRepository productRepository, LocalLanguage localLanguage) {
        this.productRepository = productRepository;
        this.localLanguage = localLanguage;
    }

    @Override
    public BaseProduct findOneById(Long id) {
        if(!this.productRepository.findById(id).isPresent())
            throw new NotFoundException(localLanguage.errors().productNotFound());
        return  this.productRepository.findById(id).get();
    }

    @Override
    public Page<BaseProduct> findAll(Pageable pageable) {
        return this.productRepository.findBaseProductsByIsEnabled(true, pageable);
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
