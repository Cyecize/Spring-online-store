package com.cyecize.skatefixers.areas.home.controllers;

import com.cyecize.skatefixers.areas.home.services.BannerService;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class HomeController extends BaseController {

    private final BannerService bannerService;

    @Autowired
    public HomeController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BannerService bannerService) {
        super(language, twigUtil, twigInformer);
        this.bannerService = bannerService;
    }

    @GetMapping("/")
    public ModelAndView homeAction(ModelAndView modelAndView){
        modelAndView.addObject("date", LocalDateTime.now());
        modelAndView.addObject("dateOld", new Date());
        modelAndView.addObject("sliderItems", bannerService.forgeIntoSlider());

        return view("default/index", modelAndView);
    }
}
