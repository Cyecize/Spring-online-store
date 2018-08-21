package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.googleDrive.util.MultipartToFileConverter;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.validators.ValidImageValidator;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.GalleryService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.http.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/gallery")
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class ImageController extends BaseController {

    private final GalleryService galleryService;

    private final BaseProductService productService;

    @Autowired
    public ImageController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, GalleryService galleryService, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.galleryService = galleryService;
        this.productService = productService;
    }


    @PostMapping("/remove")
    @ResponseBody
    public JsonResponse removeImgAction(@RequestParam(required = false, name = "imageId") Long imageId){
        this.galleryService.removeImage(this.galleryService.findImageById(imageId));
        return new JsonResponse(super.language.dictionary().imageWasRemoved());
    }

    @PostMapping(value = "/upload/{prodId:[\\d]+}", produces = "application/json")
    @ResponseBody
    public JsonResponse uploadImageAction(MultipartFile imageFile, @PathVariable Long prodId){
        if(!new ValidImageValidator().isValid(imageFile, null))
            throw new JsonException(super.language.errors().invalidImage());
        this.galleryService.uploadImage(this.productService.findOneById(prodId), MultipartToFileConverter.convert(imageFile));
        return new JsonResponse(super.language.dictionary().imageWillBeUploaded());
    }

}
