package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.language.services.LocalLanguageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LocalLanguageTests {

    private LocalLanguageImpl localLanguage;

    @BeforeEach
    public void onBefore() {
        this.localLanguage = new LocalLanguageImpl();
        localLanguage.updateLanguage(LanguageLocaleType.EN);
    }

    @Test
    public void testConstructor_expectDefaultLanguage() {
        LocalLanguage localLanguage = new LocalLanguageImpl();
        assertEquals(LanguageLocaleType.DEFAULT, localLanguage.getLocaleType(), "Locales do not match");
    }

    @Test
    public void testForName_nonExistentMethodName_returnSameValue() {
        final String msg = "I_AM_TOTALLY not Xiztent";
        assertEquals(msg, this.localLanguage.forName(msg), "returned message was not the same!");
    }

    @Test
    public void testForName_existentMethodName_returnLocaleValue() {
        this.localLanguage.updateLanguage(LanguageLocaleType.BG);
        String exp = this.localLanguage.dictionary().home();
        assertEquals(exp, localLanguage.forName(this.localLanguage.forName("home")), "text did not match");

        this.localLanguage.updateLanguage(LanguageLocaleType.EN);
        exp = this.localLanguage.dictionary().home();
        assertEquals(exp, localLanguage.forName(this.localLanguage.forName("home")), "text did not match");
    }
}












