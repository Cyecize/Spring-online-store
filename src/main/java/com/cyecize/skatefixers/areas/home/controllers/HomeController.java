package com.cyecize.skatefixers.areas.home.controllers;

import com.cyecize.skatefixers.areas.home.services.BannerService;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final BannerService bannerService;

    private final BaseProductService productService;

    @Autowired
    public HomeController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BannerService bannerService, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.bannerService = bannerService;
        this.productService = productService;

    }

    @GetMapping("/")
    public ModelAndView homeAction(ModelAndView modelAndView) {
        modelAndView.addObject("sliderItems", bannerService.forgeIntoSlider());
        modelAndView.addObject("trendingProducts", this.productService.findTrendyProducts());
        return super.view("default/index", modelAndView);
    }


}
