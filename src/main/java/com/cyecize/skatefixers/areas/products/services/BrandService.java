package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    Brand findBrandById(Long id);

    Brand findBrandByName(String brandName);
}
