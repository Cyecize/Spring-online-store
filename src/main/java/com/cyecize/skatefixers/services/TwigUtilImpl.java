package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwigUtilImpl implements TwigUtil {

    private final LocalLanguage localLanguage;

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
}
