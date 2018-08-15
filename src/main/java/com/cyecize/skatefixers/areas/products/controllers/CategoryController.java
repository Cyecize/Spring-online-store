package com.cyecize.skatefixers.areas.products.controllers;

import com.cyecize.skatefixers.areas.products.bindingModels.CategoryBindingModel;
import com.cyecize.skatefixers.areas.products.viewModels.CategoriesPageViewModel;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import com.cyecize.skatefixers.http.JsonResponse;
import com.google.api.client.json.Json;
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
@RequestMapping(value = "/categories")
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    private final BaseProductService productService;

    public CategoryController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, CategoryService categoryService, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/create")
    public ModelAndView createCategoryRequest(Model model, ModelAndView modelAndView) {
        modelAndView.addObject("parents", this.categoryService.findAll());
        Map<String, Object> map = model.asMap();
        modelAndView.addObject("currentCategory", map.get("categoryBindingModel"));
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, map.get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));

        return view("workers/add-category", modelAndView);
    }

    @PostMapping("/create")
    public String createCategoryAction(@Valid @ModelAttribute("categoryBindingModel") CategoryBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("categoryBindingModel", bindingModel);
        if (bindingResult.hasErrors())
            return "redirect:create";
        this.categoryService.createCategory(bindingModel);
        return "redirect:/worker-panel";
    }

    @GetMapping("/edit/select")
    public ModelAndView selectCategoryAction() {
        return view("workers/edit-category-select-categories", "categories", this.categoryService.findAll());
    }

    @GetMapping("/edit/{catName}")
    public ModelAndView editCategoryRequest(@PathVariable("catName") String catName, ModelAndView modelAndView, Model model) {
        modelAndView.addObject("currentCategory", this.categoryService.findOneByName(catName));
        modelAndView.addObject("parents", this.categoryService.findAll());
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        return view("workers/edit-category", modelAndView);
    }

    @PostMapping("/edit/{catName}")
    public String editCategoryAction(@Valid CategoryBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable("catName") String catName) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        Category catToEdit = this.categoryService.findOneByName(catName);
        try {
            if (!this.categoryService.findOneByName(bindingModel.getCategoryNameLatin()).getId().equals(catToEdit.getId()))
                return "redirect:/categories/edit/" + catToEdit.getCategoryNameLatin();
        } catch (NotFoundException ignored) {
        }
        try {
            if (!this.categoryService.findOneByName(bindingModel.getCategoryNameCyrillic()).getId().equals(catToEdit.getId()))
                return "redirect:/categories/edit/" + catToEdit.getCategoryNameLatin();
        } catch (NotFoundException ignored) {
        }
        this.categoryService.editCategory(catName, bindingModel);
        return "redirect:/worker-panel";
    }

    @PostMapping("/remove/{id:[\\d]+}")
    @ResponseBody
    public JsonResponse removeCategoryAction(@PathVariable("id") Long id){
        this.categoryService.removeCategory(id);
        return new JsonResponse("Category was removed");
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public ModelAndView showAllProductsAction(@PageableDefault(page = 0, size = 6) Pageable pageable) {
        return view("default/category-details", "viewModel", new CategoriesPageViewModel(
                this.categoryService.findMainCategories(),
                this.productService.findAll(pageable)
        ));
    }

    @GetMapping("/{categoryName}")
    @PreAuthorize("permitAll()")
    public ModelAndView showCategoryAction(
            @PathVariable("categoryName") String categoryName,
            @PageableDefault(page = 0, size = 6) Pageable pageable) {
        Category category = this.categoryService.findOneByName(categoryName);
        return view("default/category-details", "viewModel", new CategoriesPageViewModel(
                category.getSubCategories(),
                this.productService.findCategoryProductsRecursive(category, pageable),
                category
        ));
    }

}
