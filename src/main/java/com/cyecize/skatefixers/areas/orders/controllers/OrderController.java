package com.cyecize.skatefixers.areas.orders.controllers;


import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.services.OrderService;
import com.cyecize.skatefixers.areas.orders.viewModels.CheckoutViewModel;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.services.AddressService;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("isFullyAuthenticated()")
@RequestMapping("/checkout")
public class OrderController extends BaseController {

    private final ShoppingCartService shoppingCartService;

    private final UserService userService;

    private final AddressService addressService;

    private final OrderService orderService;

    @Autowired
    public OrderController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, ShoppingCartService shoppingCartService, UserService userService, AddressService addressService, OrderService orderService) {
        super(language, twigUtil, twigInformer);
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.addressService = addressService;
        this.orderService = orderService;
    }

    @GetMapping("")
    public ModelAndView checkoutRequest(Principal principal) {
        return super.view("default/checkout", "viewModel",
                new CheckoutViewModel(
                        this.shoppingCartService.getShoppingCart(),
                        this.addressService.findAllAddresses(this.userService.findOneByUsername(principal.getName()))
                )
        );
    }

    @PostMapping("")
    public String checkoutAction(@RequestParam(value = "addressId") Long addressId, Principal principal, RedirectAttributes redirectAttributes){
        Address address = this.addressService.findById(addressId);
        Order order = this.orderService.createOrder(this.userService.findOneByUsername(principal.getName()), address);
        redirectAttributes.addFlashAttribute("order", order);
        this.shoppingCartService.clear();
        return "redirect:/checkout/success";
    }

    @GetMapping("/success")
    public ModelAndView successAction(Model model){
        return super.view("default/order-success", "order", model.asMap().get("order"));
    }


}
