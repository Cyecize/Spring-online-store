package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.languagePacks.Dictionary;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public abstract class BaseController {

    private static final String BASE_LAYOUT_NAME = "base-layout";
    private static final String DICTIONARY_NAME = "dict";
    private static final String VIEW_MODEL_NAME = "view";
    private static final String REDIRECT_ACTION = "redirect:";

    protected final LocalLanguage language;

    @Autowired
    protected BaseController(LocalLanguage language) {
        this.language = language;
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
        modelAndView.addObject(VIEW_MODEL_NAME, viewName);
        return this.finalizeView(modelAndView);
    }

    private ModelAndView finalizeView(ModelAndView modelAndView){
        modelAndView.setViewName(BASE_LAYOUT_NAME);
        modelAndView.addObject(DICTIONARY_NAME, this.dictionary());
        return  modelAndView;
    }

    protected Dictionary dictionary(){
        return this.language.dictionary();
    }

}
