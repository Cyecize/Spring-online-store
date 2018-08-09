package com.cyecize.skatefixers.areas.language.languagePacks;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;

public interface Dictionary extends ErrorDictionary {

    LanguageLocaleType getLocaleType();

    String home();

    String contacts();

    String aboutUs();

    String login();

    String register();

    String logout();

    String go();

    String menu();

    String products();

    String categories();
}
