package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Image;

import java.io.File;

public interface GalleryService {

    void uploadImage(BaseProduct product, File image);

    void removeImage(Image image);

    Image findImageById(Long id);
}
