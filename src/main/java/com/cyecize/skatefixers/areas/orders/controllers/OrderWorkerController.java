package com.cyecize.skatefixers.areas.orders.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.orders.enums.OrderStatus;
import com.cyecize.skatefixers.areas.orders.services.OrderService;
import com.cyecize.skatefixers.areas.orders.viewModels.WorkerOrderViewModel;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class OrderWorkerController extends BaseController {

    private final OrderService orderService;

    @Autowired
    public OrderWorkerController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, OrderService orderService) {
        super(language, twigUtil, twigInformer);

        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ModelAndView allOrdersAction(){
        return view("workers/orders/all-orders", "viewModel", this.orderService.forgeWorkerOrdersViewModel(this.orderService.findAll()));
    }
    @GetMapping("/accepted")
    public ModelAndView acceptedOrdersAction(){
        return view("workers/orders/all-orders", "viewModel", this.orderService.forgeWorkerOrdersViewModel(this.orderService.findByStatus(OrderStatus.ACCEPTED)));
    }
    @GetMapping("/rejected")
    public ModelAndView rejectedOrdersAction(){
        return view("workers/orders/all-orders", "viewModel", this.orderService.forgeWorkerOrdersViewModel(this.orderService.findByStatus(OrderStatus.REJECTED)));
    }
    @GetMapping("/awaiting")
    public ModelAndView awaitingOrdersAction(){
        return view("workers/orders/all-orders", "viewModel", this.orderService.forgeWorkerOrdersViewModel(this.orderService.findByStatus(OrderStatus.AWAITING)));
    }

    @GetMapping("/review/{id:[\\d]+}")
    public ModelAndView reviewOrder(@PathVariable Long id){
        WorkerOrderViewModel workerOrderViewModel =this.orderService.forgeWorkerOrderViewModel(this.orderService.findById(id));
        return super.view("workers/orders/review-order", "viewModel", workerOrderViewModel);
    }

    @GetMapping("/accept/{id:[\\d]+}")
    public ModelAndView acceptOrderAction(@PathVariable("id") Long id){
        this.orderService.acceptOrder(this.orderService.findById(id));
        return super.redirect("/orders/review/" + id);
    }
    @GetMapping("/reject/{id:[\\d]+}")
    public ModelAndView rejectOrderAction(@PathVariable("id") Long id){
        this.orderService.rejectOrder(this.orderService.findById(id));
        return super.redirect("/orders/review/" + id);
    }
}
