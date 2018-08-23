package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.language.services.LocalLanguageImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LocalLanguageTests {

    private LocalLanguageImpl localLanguage;

    @Before
    public void onBefore(){
        this.localLanguage = new LocalLanguageImpl();
        localLanguage.updateLanguage(LanguageLocaleType.EN);
    }

    @Test
    public void testConstructor_expectDefaultLanguage(){
        LocalLanguage localLanguage = new LocalLanguageImpl();
        Assert.assertEquals("Locales do not match", LanguageLocaleType.DEFAULT, localLanguage.getLocaleType());
    }

    @Test
    public void testForName_nonExistentMethodName_returnSameValue(){
        final  String msg = "I_AM_TOTALLY not Xiztent";
        Assert.assertEquals("returned message was not the same!", msg, this.localLanguage.forName(msg));
    }

    @Test
    public void testForName_existentMethodName_returnLocaleValue(){
        this.localLanguage.updateLanguage(LanguageLocaleType.BG);
        String exp = this.localLanguage.dictionary().home();
        Assert.assertEquals("text did not match", exp, localLanguage.forName(this.localLanguage.forName("home")) );

        this.localLanguage.updateLanguage(LanguageLocaleType.EN);
         exp = this.localLanguage.dictionary().home();
        Assert.assertEquals("text did not match", exp, localLanguage.forName(this.localLanguage.forName("home")) );
    }
}












