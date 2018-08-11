package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionController extends BaseController {

    @Autowired
    public GlobalExceptionController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer) {
        super(language, twigUtil, twigInformer);
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView onNotFoundException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reason", e.getClass().getAnnotation(ResponseStatus.class).reason());
        modelAndView.addObject("message", e.getMessage());
        return super.view("errors/not-found", modelAndView);
    }

    @ExceptionHandler({JsonException.class})
    @ResponseBody
    public JsonException onJsonException(JsonException e, HttpServletResponse response){
        response.setStatus(e.getStatus().value());
        return  new JsonException(e.getMessage(), e.getBody(), e.getStatus());
    }
}
