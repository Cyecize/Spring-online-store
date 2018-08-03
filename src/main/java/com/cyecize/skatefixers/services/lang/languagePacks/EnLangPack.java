package com.cyecize.skatefixers.services.lang.languagePacks;

import com.cyecize.skatefixers.enums.LanguageLocaleType;

public class EnLangPack implements Dictionary {
    public static final String HOME = "Home";
    public static final String CONTACTS = "Contacts";
    public static final String ABOUT_US = "About us";
    public static final String LOGIN = "Login";
    public static final String REGISTER = "Register";
    public static final String LOGOUT = "Logout";
    public static final String GO = "Go";

    @Override
    public LanguageLocaleType getLocaleType() {
        return LanguageLocaleType.EN;
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
