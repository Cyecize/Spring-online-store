package com.cyecize.skatefixers.areas.products.bindingModels.converters;

import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
@EnableAutoConfiguration
public class LongToBrandConverter implements Converter<Long, Brand> {

    private final BrandService brandService;

    @Autowired
    public LongToBrandConverter(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public Brand convert(Long integer) {
        try {
            return this.brandService.findBrandById(integer);
        } catch (NotFoundException ignored) {
        }
        return null;
    }
}
