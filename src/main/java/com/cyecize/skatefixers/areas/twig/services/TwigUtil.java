package com.cyecize.skatefixers.areas.twig.services;

import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.validation.BeanPropertyBindingResult;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface TwigUtil {

    void setBindingResult(BeanPropertyBindingResult bindingResult);

    boolean hasErrors(String fieldName);

    String getLocalCategoryName(Category category);

    String getError(String fieldName);

    String translate(String text);

    DateTimeFormatter simpleFormat();

    List<String> getErrors(String fieldName);

    List<Integer> range(Object from , Object to);
}
