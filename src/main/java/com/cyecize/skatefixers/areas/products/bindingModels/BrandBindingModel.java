package com.cyecize.skatefixers.areas.products.bindingModels;

import com.cyecize.skatefixers.areas.products.bindingModels.validators.UniqueBrandName;
import com.cyecize.skatefixers.areas.products.bindingModels.validators.ValidImage;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BrandBindingModel {

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    @UniqueBrandName
    private String brandName;

    @NotNull(message = "fieldCannotBeEmpty")
    @ValidImage
    private MultipartFile file;

    public BrandBindingModel(){

    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
