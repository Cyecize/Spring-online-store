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

@Controller
public class DefaultController extends BaseController{

    private final CategoryService categoryService;

    @Autowired
    public DefaultController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, CategoryService categoryService) {
        super(language, twigUtil, twigInformer);
        this.categoryService = categoryService;
    }

    @GetMapping("/haah")
    public ModelAndView indexAction() {
        return super.view("");
    }

    @GetMapping("/")
    public ModelAndView homeAction(HttpServletRequest request){
        return view("default/index");
    }


    @GetMapping("/hoe")
    public ModelAndView homeAction(){
        return view("default/hoe");
    }


}
