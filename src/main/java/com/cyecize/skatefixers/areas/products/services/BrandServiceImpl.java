package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.repositories.BrandRepository;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final LocalLanguage localLanguage;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, LocalLanguage localLanguage) {
        this.brandRepository = brandRepository;
        this.localLanguage = localLanguage;
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
    public Brand findBrandByName(String brandName) {
        Brand brand = this.brandRepository.findBrandByBrandName(brandName);
        if (brand == null)
            throw new NotFoundException(this.localLanguage.errors().brandWithNameDoesNotExist(brandName));
        return brand;
    }
}
