package com.cyecize.skatefixers.areas.shoppingCart.bindingModels.validators;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidProductIdValidator.class)
public @interface ValidProductId {

    String message() default "productNotFound";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
