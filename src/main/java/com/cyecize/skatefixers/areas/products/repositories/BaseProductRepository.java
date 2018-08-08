package com.cyecize.skatefixers.areas.products.repositories;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {

}
