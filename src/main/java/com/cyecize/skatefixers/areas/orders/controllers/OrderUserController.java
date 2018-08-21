package com.cyecize.skatefixers.areas.orders.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.services.OrderService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@PreAuthorize("isFullyAuthenticated()")
@RequestMapping("/users/orders")
public class OrderUserController extends BaseController {

    private final UserService userService;

    private final OrderService orderService;

    @Autowired
    public OrderUserController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, UserService userService, OrderService orderService) {
        super(language, twigUtil, twigInformer);
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ModelAndView allUserOrdersAction(Principal principal){
        return view("users/orders/all-orders", "orders", this.orderService.findOrders(this.userService.findOneByUsername(principal.getName())));
    }

    @GetMapping("/review/{id:[\\d]+}")
    public ModelAndView reviewAction(Principal principal, @PathVariable Long id){
        Order order = this.orderService.findById(id);
        if(!order.getUser().getId().equals(this.userService.findOneByUsername(principal.getName()).getId()))
            throw new InternalException("Invalid order");
        return view("users/orders/order", "viewModel", this.orderService.forgeWorkerOrderViewModel(order));
    }

    @GetMapping("/{id:[\\d]+}")
    public String orderDetails(@PathVariable("id") String id){
        return "redirect:/users/orders/review/" + id;
    }
}
