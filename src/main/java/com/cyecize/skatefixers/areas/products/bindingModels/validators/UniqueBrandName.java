package com.cyecize.skatefixers.areas.products.bindingModels.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueBrandNameValidator.class)
public @interface UniqueBrandName {

    String message() default "brandNameTaken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}