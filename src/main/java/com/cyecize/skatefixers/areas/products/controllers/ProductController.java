package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.areas.products.viewModels.ProductDetailsViewModel;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.http.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class ProductController  extends BaseController {

    private final BaseProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public ProductController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BaseProductService productService, CategoryService categoryService) {
        super(language, twigUtil, twigInformer);
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/create")
    public ModelAndView createProductRequest(ModelAndView modelAndView, Model model){
        return view("workers/products/add-product", modelAndView);
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
