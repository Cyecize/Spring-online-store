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
    public static final String POPULAR_PRODUCTS = "Популярни продукти";
    public static final String DETAILS = "Детайли";
    public static final String BUY = "Купи";
    public static final String NEW_PRODUCTS = "Нови продукти";
    public static final String ALL = "Всички";
    public static final String SUB_CATEGORIES = "Под категории";
    public static final String PREVIOUS = "Предишна";
    public static final String PRODUCT_NOT_FOUND = "Продукта не беше намерен";
    public static final String BRAND = "Марка";
    public static final String QUANTITY = "Количество";
    public static final String SIMILAR_PRODUCTS = "Подобни продукти";
    public static final String PAGE_IS_EMPTY = "Страницата е празна";
    public static final String BRANDS = "Марки";
    public static final String OTHERS = "Други";
    public static final String FIELD_CANNOT_BE_EMPTY = "Полето не може да бъде празно";
    public static final String BRAND_NAME_TAKEN = "Името на марката е заето";
    public static final String BACK = "Назад";
    public static final String NEW = "Нова";
    public static final String BRAND_NAME = "Име на марка";
    public static final String IMAGE = "Снимка";
    public static final String SELECT = "Избор";
    public static final String EDIT = "Редакция";
    public static final String INVALID_IMAGE = "Невалидна снимка / формат";

    public String invalidImage() {
        return INVALID_IMAGE;
    }

    public String edit() { return EDIT; }

    public String select() { return SELECT; }

    public String image() { return IMAGE; }

    public String brandName() { return BRAND_NAME; }

    public String _new() { return NEW; }

    public String back() {
        return BACK;
    }

    public String fieldCannotBeEmpty() {
        return FIELD_CANNOT_BE_EMPTY;
    }

    public String brandNameTaken() {
        return BRAND_NAME_TAKEN;
    }

    public String brandWithNameDoesNotExist(String brandName) {
        return String.format("Не беше намерена марка с име %s.", brandName);
    }

    public String brands() {
        return BRANDS;
    }

    public String others() {
        return OTHERS;
    }

    public String pageIsEmpty() {
        return PAGE_IS_EMPTY;
    }

    public String categoryWithNameWasNotFound(String catName) {
        return String.format("Категория с име %s не беше намерена.", catName);
    }

    public String similarProducts() {
        return SIMILAR_PRODUCTS;
    }

    public String brand() {
        return BRAND;
    }

    public String quantity() {
        return QUANTITY;
    }

    public String productNotFound() {
        return PRODUCT_NOT_FOUND;
    }

    public String previous() {
        return PREVIOUS;
    }

    public String subCategories() {
        return SUB_CATEGORIES;
    }

    public String all() {
        return ALL;
    }

    public String popularProducts() {
        return POPULAR_PRODUCTS;
    }

    public String details() {
        return DETAILS;
    }

    public String buy() {
        return BUY;
    }

    public String newProducts() {
        return NEW_PRODUCTS;
    }

    public String next() {
        return NEXT;
    }

    public String seeMore() {
        return SEE_MORE;
    }

    public String search() {
        return SEARCH;
    }

    public String profile() {
        return PROFILE;
    }

    public String username() {
        return USERNAME;
    }

    public String email() {
        return EMAIL;
    }

    public String password() {
        return PASSWORD;
    }

    public String rememberMe() {
        return REMEMBER_ME;
    }

    public String usernameIsNull() {
        return USERNAME_IS_NULL;
    }

    public String usernameInvalidFormat() {
        return USERNAME_INVALID_FORMAT;
    }

    public String usernameTaken() {
        return USERNAME_TAKEN;
    }

    public String emailIsNull() {
        return EMAIL_IS_NULL;
    }

    public String emailTaken() {
        return EMAIL_IS_IN_USE;
    }

    public String passwordLengthIsLessThan() {
        return "Паролата е под " + 6 + " знака";
    }

    public String passwordsDoNotMatch() {
        return PASSWORDS_DO_NOT_MATCH;
    }

    public String passwordIsIncorrect() {
        return INCORRECT_PASSWORD;
    }

    public LanguageLocaleType getLocaleType() {
        return LanguageLocaleType.BG;
    }

    public String home() {
        return HOME;
    }

    public String contacts() {
        return CONTACTS;
    }

    public String aboutUs() {
        return ABOUT_US;
    }

    public String login() {
        return LOGIN;
    }

    public String register() {
        return REGISTER;
    }

    public String logout() {
        return LOGOUT;
    }

    public String go() {
        return GO;
    }

    public String menu() {
        return MENU;
    }

    public String products() {
        return PRODUCTS;
    }

    public String categories() {
        return CATEGORIES;
    }
}
