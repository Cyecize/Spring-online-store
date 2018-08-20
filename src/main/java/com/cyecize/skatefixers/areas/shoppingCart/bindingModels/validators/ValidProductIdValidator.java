package com.cyecize.skatefixers.areas.shoppingCart.bindingModels.validators;

import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidProductIdValidator implements ConstraintValidator<ValidProductId, Long> {

    private final BaseProductService baseProductService;

    @Autowired
    public ValidProductIdValidator(BaseProductService baseProductService) {
        this.baseProductService = baseProductService;
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        try{
            return this.baseProductService.findOneById(aLong) != null;
        }catch (Exception e){
            return false;
        }
    }
}
