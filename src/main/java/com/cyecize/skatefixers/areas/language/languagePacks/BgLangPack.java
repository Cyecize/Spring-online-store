package com.cyecize.skatefixers.areas.language.languagePacks;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;

public class BgLangPack implements Dictionary {

    public static final String HOME = "Начало";
    public static final String CONTACTS = "Контакти";
    public static final String ABOUT_US = "За нас";
    public static final String LOGIN = "Вход";
    public static final String REGISTER = "Регистрация";
    public static final String LOGOUT = "Изход";
    public static final String GO = "Напред";

    @Override
    public LanguageLocaleType getLocaleType() {
        return LanguageLocaleType.BG;
    }

    @Override
    public String home() {
        return HOME;
    }

    @Override
    public String contacts() {
        return CONTACTS;
    }

    @Override
    public String aboutUs() {
        return ABOUT_US;
    }

    @Override
    public String login() {
        return LOGIN;
    }

    @Override
    public String register() {
        return REGISTER;
    }

    @Override
    public String logout() {
        return LOGOUT;
    }

    @Override
    public String go() {
        return GO;
    }
}
