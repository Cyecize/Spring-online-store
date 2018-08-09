package com.cyecize.skatefixers.areas.language.languagePacks;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;

public class DictionaryEnImpl implements Dictionary {
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
    public static final String USERNAME_IS_NULL = "Username is empty";
    public static final String USERNAME_INVALID_FORMAT = "Invalid username format";
    public static final String USERNAME_TAKEN = "Username is in use";
    public static final String EMAIL_IS_NULL = "Email is empty";
    public static final String EMAIL_IS_IN_USE = "Email is in use";
    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match";
    public static final String INCORRECT_PASSWORD = "Incorrect password";


    @Override
    public String usernameIsNull() {
        return USERNAME_IS_NULL;
    }

    @Override
    public String usernameInvalidFormat() {
        return USERNAME_INVALID_FORMAT;
    }

    @Override
    public String usernameTaken() {
        return USERNAME_TAKEN;
    }

    @Override
    public String emailIsNull() {
        return EMAIL_IS_NULL;
    }

    @Override
    public String emailTaken() {
        return EMAIL_IS_IN_USE;
    }

    @Override
    public String passwordLengthIsLessThan() {
        return "Password is less than " + 6 + " characters long";
    }

    @Override
    public String passwordsDoNotMatch() {
        return PASSWORDS_DO_NOT_MATCH;
    }

    @Override
    public String passwordIsIncorrect() {
        return INCORRECT_PASSWORD;
    }

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