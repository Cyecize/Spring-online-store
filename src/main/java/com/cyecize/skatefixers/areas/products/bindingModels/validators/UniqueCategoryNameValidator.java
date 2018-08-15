package com.cyecize.skatefixers.areas.products.bindingModels.validators;


import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

    private final CategoryService categoryService;

    @Autowired
    public UniqueCategoryNameValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return categoryService.findOneByName(s) == null;
        } catch (NotFoundException ignored) {
        }
        return true;
    }
}
