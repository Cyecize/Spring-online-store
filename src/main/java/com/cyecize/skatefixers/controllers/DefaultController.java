package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController extends BaseController{

    @Autowired
    public DefaultController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer) {
        super(language, twigUtil, twigInformer);
    }

    @GetMapping("/home")
    public String home(){
        return "redirect:/";
    }

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "redirect:/";
    }
}
