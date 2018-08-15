package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.googleDrive.services.GoogleDriveManager;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.BrandBindingModel;
import com.cyecize.skatefixers.areas.products.bindingModels.BrandEditBindingModel;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.repositories.BrandRepository;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private static final String BRANDS_FOLDER_ID = "19HMBkZ_4KuFzssP5rcQh_d0i2JtejRho";

    private final BrandRepository brandRepository;

    private final LocalLanguage localLanguage;

    private final GoogleDriveManager driveManager;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, LocalLanguage localLanguage, GoogleDriveManager driveManager) {
        this.brandRepository = brandRepository;
        this.localLanguage = localLanguage;
        this.driveManager = driveManager;
    }

    @Override
    public List<Brand> findAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Brand findBrandById(Long id) {
        return this.brandRepository.findBrandById(id);
    }

    @Override
    public Brand findBrandByName(String brandName) throws NotFoundException {
        Brand brand = this.brandRepository.findBrandByBrandName(brandName);
        if (brand == null)
            throw new NotFoundException(this.localLanguage.errors().brandWithNameDoesNotExist(brandName));
        return brand;
    }

    @Override
    @Async
    public void createBrand(BrandBindingModel bindingModel, File convert) {
        Brand brand = new Brand();
        brand.setBrandName(bindingModel.getBrandName());
        try {
            brand.setImage(this.driveManager.uploadFile(convert, BRANDS_FOLDER_ID, "PhotoForBrand" + brand.getBrandName().split("")[0]));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        this.brandRepository.saveAndFlush(brand);
    }

    @Override
    public void editBrand(String oldBrandName, BrandEditBindingModel bindingModel) {
        Brand brand = this.findBrandByName(oldBrandName);
        brand.setBrandName(bindingModel.getBrandName());
        this.brandRepository.saveAndFlush(brand);
    }

    @Override
    @Async
    public void editBrand(String oldBrandName, BrandEditBindingModel bindingModel, File image) {
        Brand brand = this.findBrandByName(oldBrandName);
        brand.setBrandName(bindingModel.getBrandName());
        try {
            this.driveManager.deleteFile(brand.extractId());
            brand.setImage(this.driveManager.uploadFile(image, BRANDS_FOLDER_ID, "ImgForBrandId_"+brand.getId()));
            this.brandRepository.save(brand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
