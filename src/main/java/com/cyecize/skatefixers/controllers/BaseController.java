package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.languagePacks.Dictionary;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.services.TwigInformer;
import com.cyecize.skatefixers.services.TwigUtil;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    private static final String DICTIONARY_NAME = "dict";
    private static final String UTIL_NAME = "util";
    private static final String LOCALE_SMALL_NAME  = "locale";
    private static final String INFORMER_NAME = "informer";
    private static final String REDIRECT_ACTION = "redirect:";

    private final TwigInformer thymeleafBaseInformer;

    private final TwigUtil twigUtil;

    protected final LocalLanguage language;

    protected BaseController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer) {
        this.language = language;
        this.thymeleafBaseInformer = twigInformer;
        this.twigUtil = twigUtil;
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView(REDIRECT_ACTION + url);
    }

    protected ModelAndView view(String viewName) {
        return this.view(viewName, new ModelAndView());
    }

    protected ModelAndView view(String viewName, String modelName, Object model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(modelName, model);
        return this.view(viewName,modelAndView);
    }

    protected ModelAndView view(String viewName, ModelAndView modelAndView){
        modelAndView.setViewName(viewName);
        return this.finalizeView(modelAndView);
    }

    private ModelAndView finalizeView(ModelAndView modelAndView){
        modelAndView.addObject(DICTIONARY_NAME, this.dictionary());
        modelAndView.addObject(UTIL_NAME, this.twigUtil);
        modelAndView.addObject(INFORMER_NAME, this.thymeleafBaseInformer);
        modelAndView.addObject(LOCALE_SMALL_NAME, this.language.getLocaleType().getName().toLowerCase());
        return  modelAndView;
    }

    protected Dictionary dictionary(){
        return this.language.dictionary();
    }
}
