package com.cyecize.skatefixers.areas.products.bindingModels.validators;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidImageValidator implements ConstraintValidator<ValidImage, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return multipartFile != null &&
                !multipartFile.isEmpty() &&
                multipartFile.getContentType() != null &&
                (multipartFile.getContentType().equals("image/png") || multipartFile.getContentType().equals("image/jpeg"));
    }
}
