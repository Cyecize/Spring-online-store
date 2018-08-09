package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Date;

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
