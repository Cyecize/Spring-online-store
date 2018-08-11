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
    public static final String PRODUCTS = "Products";
    public static final String CATEGORIES = "Categories";
    public static final String USERNAME_IS_NULL = "Username is empty";
    public static final String USERNAME_INVALID_FORMAT = "Invalid username format";
    public static final String USERNAME_TAKEN = "Username is in use";
    public static final String EMAIL_IS_NULL = "Email is empty";
    public static final String EMAIL_IS_IN_USE = "Email is in use";
    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match";
    public static final String INCORRECT_PASSWORD = "Incorrect password";
    public static final String USERNAME = "Username";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String REMEMBER_ME = "Remember me";
    public static final String PROFILE = "Profile";
    public static final String NEXT = "Next";
    public static final String SEE_MORE = "See more";
    public static final String SEARCH = "Search";
    public static final String POPULAR_PRODUCTS = "Popular Products";
    public static final String DETAILS = "Details";
    public static final String BUY = "Buy";
    public static final String NEW_PRODUCTS = "New Products";
    public static final String ALL = "All";
    public static final String SUB_CATEGORIES = "Sub Categories";
    public static final String PREVIOUS = "Previous";
    public static final String PRODUCT_NOT_FOUND = "Product was not found";
    public static final String BRAND = "Brand";
    public static final String QUANTITY = "Quantity";
    public static final String SIMILAR_PRODUCTS = "Similar Products";
    public static final String PAGE_IS_EMPTY = "This page is empty.";
    public static final String BRANDS = "Brands";
    public static final String OTHERS = "Other";


    public String brandWithNameDoesNotExist(String brandName) {
        return String.format("Brand with name %s does not exist", brandName);
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
        return String.format("Category with name %s was not found.", catName);
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

    public String all() { return ALL; }

    public String popularProducts() { return POPULAR_PRODUCTS; }

    public String details() { return DETAILS; }

    public String buy() { return BUY; }

    public String newProducts() { return NEW_PRODUCTS; }

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
        return "Password is less than " + 6 + " characters long";
    }

    public String passwordsDoNotMatch() {
        return PASSWORDS_DO_NOT_MATCH;
    }

    public String passwordIsIncorrect() {
        return INCORRECT_PASSWORD;
    }

    public LanguageLocaleType getLocaleType() {
        return LanguageLocaleType.EN;
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

}
