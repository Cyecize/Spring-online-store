package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.services.lang.LocalLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    private final LocalLanguage localLanguage;

    @Autowired
    public DefaultController(LocalLanguage localLanguage) {
        this.localLanguage = localLanguage;
    }

    @GetMapping("/")
    @ResponseBody
    public String indexAction() {
        return String.format("Hello, my locale is %s, some examples:<br>%s<br>%s<br>%s",
                this.localLanguage.getLocaleType(),
                this.localLanguage.dictionary().aboutUs(),
                this.localLanguage.dictionary().contacts(),
                this.localLanguage.dictionary().register()
        );
    }
}
