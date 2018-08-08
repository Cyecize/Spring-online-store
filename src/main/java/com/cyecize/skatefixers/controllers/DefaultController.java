package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController extends BaseController{


    @Autowired
    public DefaultController( LocalLanguage localLanguage1) {
        super(localLanguage1);
    }

    @GetMapping("/")
    public ModelAndView indexAction() {
        System.out.println(super.language.dictionary().aboutUs());
//        return String.format("Hello, my locale is %s, some examples:<br>%s<br>%s<br>%s",
//                super.language.getLocaleType(),
//                super.language.dictionary().aboutUs(),
//                super.language.dictionary().contacts(),
//                super.language.dictionary().register()
//        );

        return super.view("");
    }
}
