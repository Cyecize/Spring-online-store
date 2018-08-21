package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.googleDrive.util.MultipartToFileConverter;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.BrandBindingModel;
import com.cyecize.skatefixers.areas.products.bindingModels.BrandEditBindingModel;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "/brands")
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class BrandController extends BaseController {

    private final BrandService brandService;

    private final BaseProductService productService;

    @Autowired
    public BrandController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BrandService brandService, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.brandService = brandService;
        this.productService = productService;
    }

    @GetMapping("/create")
    public ModelAndView addBrandRequest(ModelAndView modelAndView, Model model) {
        Map<String, Object> map = model.asMap();
        modelAndView.addObject("brandBindingModel", map.get("brandBindingModel"));
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, map.get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        return super.view("workers/add-brand", modelAndView);
    }

    @PostMapping("/create")
    public String addBrandAction(@Valid @ModelAttribute("brandBindingModel") BrandBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("brandBindingModel", bindingModel);
        if (bindingResult.hasErrors())
            return "redirect:create";
        this.brandService.createBrand(bindingModel, MultipartToFileConverter.convert(bindingModel.getFile()));
        return "redirect:/worker-panel";
    }

    @GetMapping("/find-for-edit")
    public ModelAndView findForEditAction() {
        return super.view("workers/edit-brand-select-brands", "brands", this.brandService.findAll());
    }


    @GetMapping("/edit/{brandName}")
    public ModelAndView editBrandRequest(@PathVariable String brandName, Model model, ModelAndView modelAndView) {
        Map<String, Object> map = model.asMap();
        modelAndView.addObject("brandBindingModel", map.get("brandBindingModel"));
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, map.get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        modelAndView.addObject("brand", this.brandService.findBrandByName(brandName));
        return super.view("workers/edit-brand", modelAndView);
    }

    @PostMapping("/edit/{brandName}")
    public String editBrandAction(
            @PathVariable("brandName") String brandName,
            @Valid @ModelAttribute("brandBindingModel") BrandEditBindingModel bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("brandBindingModel", bindingModel);
        if (bindingResult.hasErrors())
            return "redirect:/brands/edit/" + brandName;
        if (bindingModel.getFile().isEmpty())
            this.brandService.editBrand(brandName, bindingModel);
        else
            this.brandService.editBrand(brandName, bindingModel, MultipartToFileConverter.convert(bindingModel.getFile()));
        return "redirect:/worker-panel";
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ModelAndView allBrandsAction(ModelAndView modelAndView) {
        modelAndView.addObject("brands", this.brandService.findAll());
        return view("default/brands-all", modelAndView);
    }


    @GetMapping("/{brandName}")
    @PreAuthorize("permitAll()")
    public ModelAndView brandDetailsAction(@PathVariable("brandName") String brandName, @PageableDefault(size = 6, page = 0) Pageable pageable, ModelAndView modelAndView) {
        Brand brand = this.brandService.findBrandByName(brandName);
        modelAndView.addObject("brand", brand);
        modelAndView.addObject("products", this.productService.findProductByBrand(brand, pageable));
        return view("default/brand-details", modelAndView);
    }

}
