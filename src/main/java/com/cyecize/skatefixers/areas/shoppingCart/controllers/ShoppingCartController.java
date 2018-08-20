package com.cyecize.skatefixers.areas.shoppingCart.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.shoppingCart.bindingModels.AddCartItemBindingModel;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.http.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController extends BaseController {

    private final ShoppingCartService shoppingCartService;

    private final BaseProductService productService;

    @Autowired
    public ShoppingCartController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, ShoppingCartService shoppingCartService, BaseProductService productService) {
        super(language, twigUtil, twigInformer);
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    public JsonResponse addProductAction(@Valid AddCartItemBindingModel bindingModel, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors())
            throw new JsonException(super.language.errors().errorAddingProductToCart());
        this.shoppingCartService.addProduct(this.productService.findOneById(bindingModel.getProductId()), bindingModel.getQuantity());
        this.shoppingCartService.saveCart(response);
        return new JsonResponse(super.language.dictionary().productWasAddedToCart());
    }

    @GetMapping(value = "/size", produces = "application/json")
    @ResponseBody
    public JsonResponse getCartSize(){
        return new JsonResponse(
                "Successfully checked",
                HttpStatus.OK,
                new HashMap<String, String>(){{
                    put("size", shoppingCartService.getShoppingCartSize()+"");
                }});
    }

    @GetMapping("/remove/{id:[\\d]+}")
    @ResponseBody
    public String removeProductAction(@PathVariable Long id, HttpServletResponse response){
        this.shoppingCartService.removeProduct(id);
        this.shoppingCartService.saveCart(response);
        return "Product was cleared";
    }

    @GetMapping("/clear")
    public String clearCartAction(){
        this.shoppingCartService.clear();
        return "redirect:/cart";
    }

    @GetMapping("")
    public ModelAndView shoppingCartIndex(){
        return super.view("default/shopping-cart", "shoppingCart", this.shoppingCartService.getShoppingCart());
    }
}
