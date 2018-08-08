package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.services.TwigInformer;
import com.cyecize.skatefixers.services.TwigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class DefaultController extends BaseController{

    @Autowired
    public DefaultController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer) {
        super(language, twigUtil, twigInformer);
    }

    @GetMapping("/haah")
    public ModelAndView indexAction() {
        return super.view("");
    }

    @GetMapping("/")
    public ModelAndView homeAction(ModelAndView modelAndView){
        modelAndView.addObject("date", LocalDateTime.now());
        modelAndView.addObject("dateOld", new Date());

        return view("default/index", modelAndView);
    }


    @GetMapping("/hoe")
    public ModelAndView homeActionn(){
        return view("default/hoe");
    }


}
