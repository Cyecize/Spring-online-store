package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.bindingModels.BrandBindingModel;
import com.cyecize.skatefixers.areas.products.bindingModels.BrandEditBindingModel;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    Brand findBrandById(Long id);

    Brand findBrandByName(String brandName);

    void createBrand(BrandBindingModel bindingModel, File convert);

    void editBrand(String oldBrandName, BrandEditBindingModel bindingModel);

    void editBrand(String oldBrandName,BrandEditBindingModel bindingModel, File image);
}
