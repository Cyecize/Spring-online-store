package com.cyecize.skatefixers.areas.orders.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.notifications.services.MailService;
import com.cyecize.skatefixers.areas.notifications.services.NotificationManager;
import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.enums.OrderStatus;
import com.cyecize.skatefixers.areas.orders.services.OrderService;
import com.cyecize.skatefixers.areas.orders.viewModels.WorkerOrderViewModel;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
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

    private static final String ORDER_ACCEPTED_FORMAT = "Order with id: %s was accepted, check your profile for more info.";
    private static final String ORDER_REJECTED_FORMAT = "Order with id: %s was rejected, check your profile for more info.";

    private final OrderService orderService;

    private final MailService mailService;

    private final NotificationManager notificationManager;

    @Autowired
    public OrderWorkerController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, OrderService orderService, MailService mailService, NotificationManager notificationManager) {
        super(language, twigUtil, twigInformer);

        this.orderService = orderService;
        this.mailService = mailService;
        this.notificationManager = notificationManager;
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
        Order order =this.orderService.findById(id);
        this.orderService.acceptOrder(order);
        this.mailService.sendMessageToUser(order.getUser(), "accepted", String.format(ORDER_ACCEPTED_FORMAT, order.getId()));
        this.notificationManager.sendOrderStatusChanged(order, order.getUser());
        return super.redirect("/orders/review/" + id);
    }
    @GetMapping("/reject/{id:[\\d]+}")
    public ModelAndView rejectOrderAction(@PathVariable("id") Long id){
        Order order = this.orderService.findById(id);
        this.orderService.rejectOrder(order);
        this.mailService.sendMessageToUser(order.getUser(), "rejected", String.format(ORDER_REJECTED_FORMAT, order.getId()));
        this.notificationManager.sendOrderStatusChanged(order, order.getUser());
        return super.redirect("/orders/review/" + id);
    }
}
