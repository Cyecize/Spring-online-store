package com.cyecize.skatefixers.areas.home.bindingModel;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EditBannerBindingModel {

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    private String title;

    @NotNull(message = "fieldCannotBeEmpty")
    private MultipartFile file;

    private boolean isDisabled;

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    @Length(max = 254)
    private String url;

    public EditBannerBindingModel(){

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

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
