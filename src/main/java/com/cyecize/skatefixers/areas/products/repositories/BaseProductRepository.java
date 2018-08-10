package com.cyecize.skatefixers.areas.products.repositories;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {

    List<BaseProduct> findTop6ByOrderByWeeklyViewsDesc();

    Page<BaseProduct> findBaseProductsByIsEnabled(Boolean isEnabled, Pageable pageable);

    @Query(value = "SELECT * FROM  products ORDER BY id DESC LIMIT ?1", nativeQuery = true)
    List<BaseProduct> findNewProductsLimit(int limit);

}
