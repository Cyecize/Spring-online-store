package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/brands", method = {RequestMethod.GET, RequestMethod.POST})
public class BrandController extends BaseController {

    private final BrandService brandService;

    private final BaseProductService productService;

    @Autowired
    public BrandController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BrandService brandService, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.brandService = brandService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public ModelAndView allBrandsAction() {
        return null;
    }

    @GetMapping("/{brandName}")
    public ModelAndView brandDetailsAction(
            @PathVariable("brandName") String brandName,
            @PageableDefault(size = 6, page = 0) Pageable pageable,
            ModelAndView modelAndView) {
        Brand brand = this.brandService.findBrandByName(brandName);
        modelAndView.addObject("brand", brand);
        modelAndView.addObject("products", this.productService.findProductByBrand(brand, pageable));
        return view("default/brand-details", modelAndView);
    }

}
