package com.cyecize.skatefixers.areas.products.bindingModels.converters;

import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
public class LongToCategoryConverter implements Converter<Long, Category> {

    private final CategoryService categoryService;

    @Autowired
    public LongToCategoryConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category convert(Long lng) {
        try{
            return this.categoryService.findOneById(lng);
        }catch (NotFoundException ignored){}
        return null;
    }
}
