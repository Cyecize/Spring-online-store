package com.cyecize.skatefixers.areas.language.languagePacks;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;

public class DictionaryBgImpl implements Dictionary {

    public static final String HOME = "Начало";
    public static final String CONTACTS = "Контакти";
    public static final String ABOUT_US = "За нас";
    public static final String LOGIN = "Вход";
    public static final String REGISTER = "Регистрация";
    public static final String LOGOUT = "Изход";
    public static final String GO = "Напред";
    public static final String MENU = "Меню";
    public static final String PRODUCTS = "Продукти";
    public static final String CATEGORIES = "Категории";
    public static final String USERNAME_IS_NULL = "Потребителското име е празно";
    public static final String USERNAME_INVALID_FORMAT = "Невалиден формат на потр.име";
    public static final String USERNAME_TAKEN = "Потр. име е заето";
    public static final String EMAIL_IS_NULL = "Email адресът е празен";
    public static final String EMAIL_IS_IN_USE = "Email адресът е зает";
    public static final String PASSWORDS_DO_NOT_MATCH = "Паролите не съвпадат";
    public static final String INCORRECT_PASSWORD = "Грешна парола";
    public static final String USERNAME = "Потр. име";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Парола";
    public static final String REMEMBER_ME = "Запомни ме";
    public static final String PROFILE = "Моят профил";
    public static final String NEXT = "Сладваща";
    public static final String SEE_MORE = "Подробности";
    public static final String SEARCH = "Търсене";


    @Override
    public String next() {
        return NEXT;
    }

    @Override
    public String seeMore() {
        return SEE_MORE;
    }

    @Override
    public String search() {
        return SEARCH;
    }

    @Override
    public String profile() {
        return PROFILE;
    }

    @Override
    public String username() {
        return USERNAME;
    }

    @Override
    public String email() {
        return EMAIL;
    }

    @Override
    public String password() {
        return PASSWORD;
    }

    @Override
    public String rememberMe() {
        return REMEMBER_ME;
    }



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
        return "Паролата е под " + 6 + " знака";
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
