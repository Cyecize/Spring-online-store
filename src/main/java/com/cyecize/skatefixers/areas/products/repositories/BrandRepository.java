package com.cyecize.skatefixers.areas.products.repositories;

import com.cyecize.skatefixers.areas.products.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findBrandById(Long id);

    Brand findBrandByBrandName(String brandName);
}
