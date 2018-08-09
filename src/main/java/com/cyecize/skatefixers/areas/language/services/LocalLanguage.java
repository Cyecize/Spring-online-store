package com.cyecize.skatefixers.areas.language.services;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;
import com.cyecize.skatefixers.areas.language.languagePacks.Dictionary;
import com.cyecize.skatefixers.areas.language.languagePacks.ErrorDictionary;

public interface LocalLanguage {

    Dictionary dictionary();

    ErrorDictionary errors();

    LanguageLocaleType getLocaleType();

    String forName(String phraseName);

    void updateLanguage(LanguageLocaleType localeType);

}
