package com.cyecize.skatefixers.areas.language.languagePacks;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;

public class EnLangPack implements Dictionary {
    public static final String HOME = "Home";
    public static final String CONTACTS = "Contacts";
    public static final String ABOUT_US = "About us";
    public static final String LOGIN = "Login";
    public static final String REGISTER = "Register";
    public static final String LOGOUT = "Logout";
    public static final String GO = "Go";
    public static final String MENU = "Menu";
    public static final String PRODUCTS = "Product";
    public static final String CATEGORIES = "Categories";

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

    @Override
    public String menu() {
        return MENU;
    }

    @Override
    public String products() {
        return PRODUCTS;
    }

    @Override
    public String categories() {
        return CATEGORIES;
    }
}
