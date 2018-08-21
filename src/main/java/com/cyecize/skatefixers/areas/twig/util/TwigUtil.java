package com.cyecize.skatefixers.areas.twig.util;

import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.validation.BeanPropertyBindingResult;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface TwigUtil {

    void setBindingResult(BeanPropertyBindingResult bindingResult);

    boolean hasErrors(String fieldName);

    boolean hasUserRole(String role, User user);

    String getLocalCategoryName(Category category);

    String getError(String fieldName);

    String translate(String text);

    String getRoles(User user);

    DateTimeFormatter simpleFormat();

    List<String> getErrors(String fieldName);

    List<Integer> range(Object from , Object to);
}
