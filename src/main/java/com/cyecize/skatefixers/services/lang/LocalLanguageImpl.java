package com.cyecize.skatefixers.services.lang;

import com.cyecize.skatefixers.enums.LanguageLocaleType;
import com.cyecize.skatefixers.services.lang.languagePacks.BgLangPack;
import com.cyecize.skatefixers.services.lang.languagePacks.Dictionary;
import com.cyecize.skatefixers.services.lang.languagePacks.EnLangPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LocalLanguageImpl implements LocalLanguage {

    private Dictionary dictionary;

    private final HttpServletRequest request;

    @Autowired
    public LocalLanguageImpl(HttpServletRequest request) {
        this.dictionary = new EnLangPack();
        this.request = request;
    }


    @Override
    public Dictionary dictionary() {
        System.out.println(this.request + " this request");
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
}
