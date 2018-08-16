package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.googleDrive.util.MultipartToFileConverter;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.CreateProductBindingModel;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.areas.products.viewModels.CreateEditProductViewModel;
import com.cyecize.skatefixers.areas.products.viewModels.ProductDetailsViewModel;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.http.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/products")
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class ProductController  extends BaseController {

    private final BaseProductService productService;

    private final CategoryService categoryService;

    private final BrandService brandService;

    @Autowired
    public ProductController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BaseProductService productService, CategoryService categoryService, BrandService brandService) {
        super(language, twigUtil, twigInformer);
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @GetMapping("/create")
    public ModelAndView createProductRequest(ModelAndView modelAndView, Model model){
        modelAndView.addObject("product", model.asMap().get("productBindingModel"));
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        modelAndView.addObject("viewModel", new CreateEditProductViewModel(this.categoryService.findAll(), this.brandService.findAll()));
        return view("workers/products/add-product", modelAndView);
    }

    @PostMapping("/create")
    public String createProductAction(@Valid @ModelAttribute("productBindingModel")CreateProductBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("productBindingModel", bindingModel);
        if(bindingResult.hasErrors())
            return "redirect:/products/create";
        this.productService.createProduct(bindingModel, MultipartToFileConverter.convert(bindingModel.getFile()));
        return "redirect:/worker-panel";
    }

    @GetMapping("/{id:[\\d+]+}")
    @PreAuthorize("permitAll()")
    public ModelAndView productDetails(@PathVariable Long id){
        BaseProduct product = this.productService.findOneById(id);
        return super.view("default/product-details", "viewModel",
                new ProductDetailsViewModel(
                        product,
                        this.categoryService.findAllParentCategories(product.getCategory()),
                        this.productService.findSimilarProducts(product, 6)
                        ));
    }

    @PostMapping(value = "/view/{id:[\\d+]+}", produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll()")
    public JsonResponse viewProductAction(@PathVariable("id") Long id){
        this.productService.viewProduct(id);
        return new JsonResponse("View was added");
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public String allProds(){
        return "redirect:/categories";
    }
}
