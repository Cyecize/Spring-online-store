package com.cyecize.skatefixers.services.lang.languagePacks;

import com.cyecize.skatefixers.enums.LanguageLocaleType;

public interface Dictionary {

    LanguageLocaleType getLocaleType();

    String home();

    String contacts();

    String aboutUs();

    String login();

    String register();

    String logout();

    String go();
}
