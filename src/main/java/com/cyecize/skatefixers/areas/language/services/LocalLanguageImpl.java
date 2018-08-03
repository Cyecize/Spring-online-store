package com.cyecize.skatefixers.areas.language.services;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;
import com.cyecize.skatefixers.areas.language.languagePacks.BgLangPack;
import com.cyecize.skatefixers.areas.language.languagePacks.Dictionary;
import com.cyecize.skatefixers.areas.language.languagePacks.EnLangPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Service
public class LocalLanguageImpl implements LocalLanguage {

    private Dictionary dictionary;

    public LocalLanguageImpl() {
        this.updateLanguage(LanguageLocaleType.DEFAULT);
    }

    @Override
    public Dictionary dictionary() {
        return this.dictionary;
    }

    @Override
    public LanguageLocaleType getLocaleType() {
        return this.dictionary.getLocaleType();
    }

    @Override
    public String forName(String phraseName) {
        try {
            return (String) this.dictionary.getClass().getDeclaredMethod(phraseName).invoke(this.dictionary);
        } catch (Exception ignored) {
        }
        return phraseName;
    }

    @Override
    public void updateLanguage(LanguageLocaleType localeType) {

        if (this.dictionary != null && localeType == this.dictionary.getLocaleType())
            return;
        switch (localeType) {
            case BG:
                this.dictionary = new BgLangPack();
                break;
            case EN:
                this.dictionary = new EnLangPack();
                break;
        }
    }
}
