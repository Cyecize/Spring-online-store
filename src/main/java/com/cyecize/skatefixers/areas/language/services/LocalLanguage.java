package com.cyecize.skatefixers.areas.language.services;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;
import com.cyecize.skatefixers.areas.language.languagePacks.Dictionary;

public interface LocalLanguage {

    Dictionary dictionary();

    LanguageLocaleType getLocaleType();

    String forName(String phraseName);

    void updateLanguage(LanguageLocaleType localeType);

}
