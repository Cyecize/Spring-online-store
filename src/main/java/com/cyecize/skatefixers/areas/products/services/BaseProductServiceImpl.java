package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.products.Product;
import com.cyecize.skatefixers.areas.products.repositories.BaseProductRepository;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseProductServiceImpl implements BaseProductService {

    private final BaseProductRepository productRepository;

    private final LocalLanguage localLanguage;

    @Autowired
    public BaseProductServiceImpl(BaseProductRepository productRepository, LocalLanguage localLanguage) {
        this.productRepository = productRepository;
        this.localLanguage = localLanguage;
    }

    @Override
    public void viewProduct(Long productId) {
        if(!this.productRepository.findById(productId).isPresent())
            throw new JsonException("Product was not found", null, HttpStatus.OK);
        BaseProduct product = this.findOneById(productId);
        product.setWeeklyViews(product.getWeeklyViews() + 1);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public BaseProduct findOneById(Long id) {
        if (!this.productRepository.findById(id).isPresent())
            throw new NotFoundException(localLanguage.errors().productNotFound());
        return this.productRepository.findById(id).get();
    }

    @Override
    public Page<BaseProduct> findAll(Pageable pageable) {
        return this.productRepository.findBaseProductsByIsEnabled(true, pageable);
    }

    @Override
    public List<BaseProduct> findSimilarProducts(BaseProduct product, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return this.productRepository.findBaseProductsByCategoryOrderByWeeklyViewsDesc(product.getCategory(), pageable).stream()
                .filter(p -> !p.getId().equals(product.getId())).collect(Collectors.toList());
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
