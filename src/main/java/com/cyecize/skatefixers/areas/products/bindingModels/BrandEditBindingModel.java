package com.cyecize.skatefixers.areas.products.bindingModels;

import com.cyecize.skatefixers.areas.products.bindingModels.validators.UniqueBrandName;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BrandEditBindingModel {
    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    @UniqueBrandName
    private String brandName;

    @NotNull(message = "fieldCannotBeEmpty")
    private MultipartFile file;

    public BrandEditBindingModel(){

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
