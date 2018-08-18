package com.cyecize.skatefixers.areas.products.services;

import com.cyecize.skatefixers.areas.googleDrive.services.GoogleDriveManager;
import com.cyecize.skatefixers.areas.googleDrive.util.ImageIdExtractor;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Image;
import com.cyecize.skatefixers.areas.products.repositories.ImageRepository;
import com.cyecize.skatefixers.exceptions.JsonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

@Service
public class GalleryServiceImpl implements GalleryService {

    private static final String GALLERY_FOLDER_ID = "1TABKzNzrd5aNjLH3qihRYvtliEUm63qY";

    private final ImageRepository imageRepository;

    private final GoogleDriveManager googleDriveManager;

    @Autowired
    public GalleryServiceImpl(ImageRepository imageRepository, GoogleDriveManager googleDriveManager) {
        this.imageRepository = imageRepository;
        this.googleDriveManager = googleDriveManager;
    }

    @Override
    @Async
    public void uploadImage(BaseProduct product, File image) {
        Image entity = new Image();
        try {
            entity.setImageUrl(ImageIdExtractor.setFormat(this.googleDriveManager.uploadFile(image, GALLERY_FOLDER_ID, "ImgForProdId_" + product.getId())));
            entity.setProduct(product);

            this.imageRepository.saveAndFlush(entity);
        } catch (Exception e) {
            this.googleDriveManager.deleteFile(ImageIdExtractor.extractId(entity.getImageUrl()));
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void removeImage(Image image) {
        this.googleDriveManager.deleteFile(ImageIdExtractor.extractId(image.getImageUrl()));
        this.imageRepository.delete(image);
    }

    @Override
    public Image findImageById(Long id) {
        if(!this.imageRepository.findById(id).isPresent())
            throw new JsonException("Image was not found", new HashMap<>(), HttpStatus.NOT_FOUND);
        return this.imageRepository.findById(id).get();
    }
}
