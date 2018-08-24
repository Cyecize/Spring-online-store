package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.languagePacks.Dictionary;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    private static final String DICTIONARY_NAME = "dict";
    private static final String UTIL_NAME = "util";
    private static final String LOCALE_SMALL_NAME  = "locale";
    private static final String INFORMER_NAME = "informer";
    private static final String REDIRECT_ACTION = "redirect:";

    private final TwigInformer twigInformer;

    private final TwigUtil twigUtil;

    protected final LocalLanguage language;

    @Autowired
    private HttpServletRequest request;

    protected BaseController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer) {
        this.language = language;
        this.twigInformer = twigInformer;
        this.twigUtil = twigUtil;
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ;
    }

    private ModelAndView finalizeView(ModelAndView modelAndView){
        if(modelAndView.getModel().containsKey(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME))
            this.twigUtil.setBindingResult((BeanPropertyBindingResult) modelAndView.getModelMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        if(this.isAuthenticated())
            this.twigInformer.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        else
            this.twigInformer.setUser(null);
        modelAndView.addObject(DICTIONARY_NAME, this.dictionary());
        modelAndView.addObject(UTIL_NAME, this.twigUtil);
        modelAndView.addObject(INFORMER_NAME, this.twigInformer);
        modelAndView.addObject(LOCALE_SMALL_NAME, this.language.getLocaleType().getName().toLowerCase());
        return  modelAndView;
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

    protected Dictionary dictionary(){
        return this.language.dictionary();
    }
}
