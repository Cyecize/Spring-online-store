package com.cyecize.skatefixers.areas.language.interceptors;

import com.cyecize.skatefixers.areas.language.Constants;
import com.cyecize.skatefixers.areas.language.annotations.LocalLang;
import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.constants.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class LocaleInterceptor extends HandlerInterceptorAdapter {

    private final LocalLanguage localLanguage;

    @Autowired
    public LocaleInterceptor(LocalLanguage localLanguage) {

        this.localLanguage = localLanguage;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod))
            return true;
        Method method = ((HandlerMethod) handler).getMethod();
        if (!method.isAnnotationPresent(LocalLang.class)) {
            this.localLanguage.updateLanguage(this.extractLangFromCookie(request, response));
            return true;
        }
        this.localLanguage.updateLanguage(method.getAnnotation(LocalLang.class).langType());
        return true;
    }

    private LanguageLocaleType extractLangFromCookie(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            this.initCookie(response);
            return LanguageLocaleType.DEFAULT;
        }
        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(WebConstants.COOKIE_LANG_NAME))
                .findFirst().orElse(null);
        if (cookie == null) {
            this.initCookie(response);
            return LanguageLocaleType.DEFAULT;
        }
        LanguageLocaleType localeType =  Arrays.stream(LanguageLocaleType.values())
                .filter(lt -> lt.name().equals(cookie.getValue().toUpperCase()))
                .findFirst().orElse(null);
        if(localeType == null){
            this.initCookie(response);
            return LanguageLocaleType.DEFAULT;
        }

        return localeType;
    }

    private void initCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(WebConstants.COOKIE_LANG_NAME, LanguageLocaleType.DEFAULT.getName());
        cookie.setPath("/");
        cookie.setMaxAge(Constants.COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }
}
