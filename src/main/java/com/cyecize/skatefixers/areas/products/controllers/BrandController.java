package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.googleDrive.services.GoogleDriveManager;
import com.cyecize.skatefixers.areas.googleDrive.util.MultipartToFileConverter;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.BrandBindingModel;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.http.JsonResponse;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/brands")
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
    public ModelAndView allBrandsAction(ModelAndView modelAndView) {
        modelAndView.addObject("brands", this.brandService.findAll());
        return view("default/brands-all", modelAndView);
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_WORKER')")
    public ModelAndView addBrandRequest(ModelAndView modelAndView, Model model) {
        Map<String, Object> map = model.asMap();
        modelAndView.addObject("brandBindingModel", map.get("brandBindingModel"));
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, map.get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        return super.view("workers/add-brand", modelAndView);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_WORKER')")
    public String addBrandAction(@Valid @ModelAttribute("brandBindingModel") BrandBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("brandBindingModel", bindingModel);
        if (bindingResult.hasErrors())
            return "redirect:create";
        this.brandService.createBrand(bindingModel, MultipartToFileConverter.convert(bindingModel.getFile()));
        return "redirect:all";
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
