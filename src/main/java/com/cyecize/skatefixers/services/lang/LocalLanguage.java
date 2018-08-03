package com.cyecize.skatefixers.services.lang;

import com.cyecize.skatefixers.enums.LanguageLocaleType;
import com.cyecize.skatefixers.services.lang.languagePacks.Dictionary;

public interface LocalLanguage {

    Dictionary dictionary();

    LanguageLocaleType getLocaleType();

    String forName(String phraseName);

}
