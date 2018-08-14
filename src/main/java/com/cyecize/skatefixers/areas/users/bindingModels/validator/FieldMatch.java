package com.cyecize.skatefixers.areas.users.bindingModels.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch
{
    String message() default "The fields must match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String first();
    String second();

//    @Target(ElementType.TYPE)
//    @Retention(RetentionPolicy.RUNTIME)
//    @Documented
//    @interface List
//    {
//        FieldMatch[] value();
//    }
}