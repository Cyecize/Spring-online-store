package com.cyecize.skatefixers.areas.language.enums;

public enum LanguageLocaleType {
    BG, EN;

    public static final LanguageLocaleType DEFAULT = LanguageLocaleType.EN;

    LanguageLocaleType(){

    }

    public String getName(){
        return this.name().toLowerCase();
    }
}
