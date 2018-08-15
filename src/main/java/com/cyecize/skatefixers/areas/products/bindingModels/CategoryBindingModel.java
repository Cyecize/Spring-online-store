package com.cyecize.skatefixers.areas.products.bindingModels;

import com.cyecize.skatefixers.areas.products.bindingModels.validators.UniqueCategoryName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryBindingModel {

    private Long parentCategoryId;

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    @UniqueCategoryName
    private String categoryNameLatin;

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty")
    @UniqueCategoryName
    private String categoryNameCyrillic;

    public CategoryBindingModel(){

    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getCategoryNameLatin() {
        return categoryNameLatin;
    }

    public void setCategoryNameLatin(String categoryNameLatin) {
        this.categoryNameLatin = categoryNameLatin;
    }

    public String getCategoryNameCyrillic() {
        return categoryNameCyrillic;
    }

    public void setCategoryNameCyrillic(String categoryNameCyrillic) {
        this.categoryNameCyrillic = categoryNameCyrillic;
    }
}
