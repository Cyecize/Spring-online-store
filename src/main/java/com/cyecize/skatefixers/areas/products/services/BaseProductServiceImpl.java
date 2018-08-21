package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.googleDrive.services.GoogleDriveManager;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.CreateProductBindingModel;
import com.cyecize.skatefixers.areas.products.bindingModels.EditProductBindingModel;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.entities.products.Product;
import com.cyecize.skatefixers.areas.products.repositories.BaseProductRepository;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import com.cyecize.skatefixers.util.ModelMerger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BaseProductServiceImpl implements BaseProductService {

    private static final String PRODUCTS_FOLDER_ID = "1iib1PmVquOSNJEfTOi53QO24V9juDNIG";

    private static final String IMAGE_PREFIX = "ImgForProductId%s";

    private final BaseProductRepository productRepository;

    private final LocalLanguage localLanguage;

    private final ModelMapper modelMapper;

    private final ModelMerger modelMerger;

    private final GoogleDriveManager googleDriveManager;

    @Autowired
    public BaseProductServiceImpl(BaseProductRepository productRepository, LocalLanguage localLanguage, ModelMapper modelMapper, ModelMerger modelMerger, GoogleDriveManager googleDriveManager) {
        this.productRepository = productRepository;
        this.localLanguage = localLanguage;
        this.modelMapper = modelMapper;
        this.modelMerger = modelMerger;
        this.googleDriveManager = googleDriveManager;
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
    public void hideProduct(BaseProduct product) {
        product.setEnabled(false);
        this.productRepository.save(product);
    }

    @Override
    public void save(BaseProduct product) {
        this.productRepository.save(product);
    }

    @Override
    public void createProduct(CreateProductBindingModel bindingModel, File image) {
        BaseProduct product = this.modelMapper.map(bindingModel, Product.class);
        this.productRepository.save(product);
        try {
            product.setImage(this.googleDriveManager.uploadFile(image, PRODUCTS_FOLDER_ID, String.format(IMAGE_PREFIX, product.getId())));
            this.productRepository.save(product);
        } catch (IOException e) {
            e.printStackTrace();
    }
    }

    @Override
    public void enableOrDisableProduct(BaseProduct product, boolean isEnabled) {
        product.setEnabled(isEnabled);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void editProduct(EditProductBindingModel bindingModel, Long id) {
        BaseProduct product = this.findOneById(id);
        product = this.modelMerger.merge(bindingModel, product);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    @Async
    public void editProduct(EditProductBindingModel bindingModel, File image, Long id) {
        this.editProduct(bindingModel, id);
        BaseProduct product = this.findOneById(id);
        this.googleDriveManager.deleteFile(product.extractId());
        try{
            product.setImage(this.googleDriveManager.uploadFile(image, PRODUCTS_FOLDER_ID, String.format(IMAGE_PREFIX, product.getId())));
            this.productRepository.saveAndFlush(product);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public BaseProduct findOneById(Long id) {
        if (!this.productRepository.findById(id).isPresent())
            throw new NotFoundException(localLanguage.errors().productNotFound());
        return this.productRepository.findById(id).get();
    }

    @Override
    public Page<BaseProduct> findAll(Pageable pageable) {
        return this.productRepository.findBaseProductsByIsEnabledTrueOrderByWeeklyViewsDesc( pageable);
    }

    @Override
    public Page<BaseProduct> findCategoryProductsRecursive(Category category, Pageable pageable) {
        List<BaseProduct> products = category.activeProductsRecursive();
        return new PageImpl<>(
                products.stream()
                        .sorted(((o1, o2) -> o2.getId().compareTo(o1.getId())))
                        .skip(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .collect(Collectors.toList()),
                pageable,
                products.size()
        );
    }

    @Override
    public Page<BaseProduct> findProductByBrand(Brand brand, Pageable pageable) {
        return this.productRepository.findBaseProductsByBrandAndIsEnabled(brand, true,pageable);
    }

    @Override
    public List<BaseProduct> findSimilarProducts(BaseProduct product, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return this.productRepository.findBaseProductsByCategoryAndIsEnabledOrderByWeeklyViewsDesc(product.getCategory(),true, pageable).stream()
                .filter(p -> !p.getId().equals(product.getId())).collect(Collectors.toList());
    }

    @Override
    public List<BaseProduct> findAllIncludeDisabled() {
        return productRepository.findAll();
    }

    @Override
    public List<BaseProduct> findTrendyProducts() {
        return productRepository.findTop6ByIsEnabledOrderByWeeklyViewsDesc(true);
    }

    @Override
    public List<BaseProduct> findNewProducts(int limit) {
        return productRepository.findNewProductsLimit(limit);
    }
}
