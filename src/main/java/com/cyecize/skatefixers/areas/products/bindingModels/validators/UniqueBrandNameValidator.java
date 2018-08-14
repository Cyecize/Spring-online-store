package com.cyecize.skatefixers.areas.products.bindingModels.validators;


import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {

    private final BrandService brandService;

    @Autowired
    public UniqueBrandNameValidator(BrandService brandService){
        this.brandService = brandService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return brandService.findBrandByName(s) == null;
        } catch (NotFoundException e) {
            return true;
        }
    }
}
