package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.annotations.LocalLang;
import com.cyecize.skatefixers.areas.language.enums.LanguageLocaleType;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController extends BaseController{


    @Autowired
    public DefaultController(LocalLanguage localLanguage) {
        super(localLanguage);
    }

    @GetMapping("/")
    public String indexAction() {
//        return String.format("Hello, my locale is %s, some examples:<br>%s<br>%s<br>%s",
//                super.language.getLocaleType(),
//                super.language.dictionary().aboutUs(),
//                super.language.dictionary().contacts(),
//                super.language.dictionary().register()
//        );

        return "base-layout";
    }
}
