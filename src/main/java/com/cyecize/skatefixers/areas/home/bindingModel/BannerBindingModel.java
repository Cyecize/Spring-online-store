package com.cyecize.skatefixers.areas.home.bindingModel;

import com.cyecize.skatefixers.areas.products.bindingModels.validators.ValidImage;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BannerBindingModel {

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    private String title;

    @NotNull(message = "fieldCannotBeEmpty")
    @ValidImage
    private MultipartFile file;

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    @Length(max = 254)
    private String url;

    public BannerBindingModel(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
