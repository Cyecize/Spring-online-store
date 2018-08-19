package com.cyecize.skatefixers.areas.home.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.repositories.BaseProductRepository;
import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private final CategoryService categoryService;

    private final BrandService brandService;

    private final BaseProductRepository productRepository;

    @Autowired
    public SearchServiceImpl(CategoryService categoryService, BrandService brandService, BaseProductRepository productRepository) {
        this.categoryService = categoryService;
        this.brandService = brandService;

        this.productRepository = productRepository;
    }


    @Override
    public String searchNonProducts(String text) {

        try{
            Category category = this.categoryService.findOneByName(text);
            if(category != null)
                return "/categories/" + category.getCategoryNameLatin();
        }catch (NotFoundException ignored){}

          try{
            Brand brand = this.brandService.findBrandByName(text);
            if(brand != null)
                return "/brands/" + brand.getBrandName();
        }catch (NotFoundException ignored){}


        return null;
    }

    @Override
    public Page<BaseProduct> searchProducts(String text, Pageable pageable) {
        text = text.replace(" ", "%");
        return this.productRepository.searchEnabled( text, pageable);
    }

    @Override
    public Page<BaseProduct> searchAllProducts(String text, Pageable pageable) {
        text = text.replace(" ", "%");
        return this.productRepository.searchAll(text, pageable);
    }
}
