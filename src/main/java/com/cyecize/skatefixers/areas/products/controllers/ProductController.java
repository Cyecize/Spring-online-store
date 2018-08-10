package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/products", method = {RequestMethod.GET, RequestMethod.POST})
public class ProductController  extends BaseController {

    private final BaseProductService productService;

    @Autowired
    public ProductController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.productService = productService;
    }

    @GetMapping("/{id:[\\d+]+}")
    public ModelAndView productDetails(@PathVariable Long id){
        BaseProduct product = this.productService.findOneById(id);
        return super.view("");
    }

    @GetMapping("/all")
    public String allProds(){
        return "redirect:/categories";
    }
}
