package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.products.viewModels.CategoriesPageViewModel;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/categories", method = {RequestMethod.GET, RequestMethod.POST})
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    private final BaseProductService productService;

    public CategoryController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, CategoryService categoryService, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("")
    public ModelAndView showAllProductsAction(@PageableDefault(page = 0, size = 6) Pageable pageable) {
        return view("default/category-details", "viewModel", new CategoriesPageViewModel(
                this.categoryService.findMainCategories(),
                this.productService.findAll(pageable)
                ));
    }

    @GetMapping("/{categoryName}")
    public ModelAndView showCategoryAction(
            @PathVariable("categoryName") String categoryName,
            @PageableDefault(page = 0, size = 6) Pageable pageable)
    {
        Category category = this.categoryService.findOneByName(categoryName);
        return view("default/category-details", "viewModel", new CategoriesPageViewModel(
                category.getSubCategories(),
                this.productService.findCategoryProductsRecursive(category, pageable),
                category
        ));
    }

}
