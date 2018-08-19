package com.cyecize.skatefixers.areas.products.repositories;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {


    List<BaseProduct> findTop6ByIsEnabledOrderByWeeklyViewsDesc(Boolean isEnabled);

    Page<BaseProduct> findBaseProductsByIsEnabled(Boolean isEnabled, Pageable pageable);

    Page<BaseProduct> findBaseProductsByBrandAndIsEnabled(Brand brand, Boolean isEnabled, Pageable pageable);

    @Query("SELECT p FROM BaseProduct p WHERE p.productName LIKE %:text% OR  p.description LIKE %:text% ORDER BY p.weeklyViews DESC ")
    Page<BaseProduct> searchAll(@Param("text") String text, Pageable pageable);

    @Query("SELECT p FROM BaseProduct p WHERE  p.isEnabled = true AND (p.productName LIKE %:text% OR  p.description LIKE %:text%) ORDER BY p.weeklyViews DESC ")
    Page<BaseProduct> searchEnabled(@Param("text") String text, Pageable pageable);

    @Query(value = "SELECT  * FROM products WHERE is_enabled = TRUE ORDER BY id DESC LIMIT ?1", nativeQuery = true)
    List<BaseProduct> findNewProductsLimit(int limit);

    List<BaseProduct> findBaseProductsByCategoryAndIsEnabledOrderByWeeklyViewsDesc(Category category, Boolean isEnabled, Pageable pageable);



}
