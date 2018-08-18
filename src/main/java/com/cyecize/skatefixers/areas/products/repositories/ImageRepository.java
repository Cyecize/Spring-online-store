package com.cyecize.skatefixers.areas.products.repositories;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository  extends JpaRepository<Image, Long> {

    List<Image> findImagesByProduct(BaseProduct product);
}
