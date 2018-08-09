package com.cyecize.skatefixers.areas.twig.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TwigUtilImpl implements TwigUtil {

    private final LocalLanguage localLanguage;

    private BeanPropertyBindingResult bindingResult;

    @Autowired
    public TwigUtilImpl(LocalLanguage localLanguage) {
        this.localLanguage = localLanguage;
    }

    @Override
    public String getLocalCategoryName(Category category) {
        switch (localLanguage.getLocaleType()){
            case BG:
                return category.getCategoryNameCyrillic();
            case EN:
                return category.getCategoryNameLatin();
        }
        return "Undefined locale";
    }

    @Override
    public void setBindingResult(BeanPropertyBindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    @Override
    public boolean hasErrors(String fieldName) {
        if(this.bindingResult == null)
            return false;
        for (FieldError error : bindingResult.getFieldErrors())
            if(error.getField().equals(fieldName))
                return true;
        return false;
    }

    @Override
    public String getError(String fieldName) {
        return String.join(", ", this.getErrors(fieldName));
    }

    @Override
    public DateTimeFormatter simpleFormat() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public List<String> getErrors(String fieldName) {
        if(bindingResult == null) return null;
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            if(fieldError.getField().equals(fieldName))
                errors.add(this.localLanguage.forName(fieldError.getDefaultMessage()));
        }
        return errors;
    }

    @Override
    public String translate(String text) {
        return this.localLanguage.forName(text);
    }


}
