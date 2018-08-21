package com.cyecize.skatefixers.areas.notifications.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.notifications.services.NotificationService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@PreAuthorize("isFullyAuthenticated()")
@RequestMapping("/notifications")
public class NotificationController extends BaseController {

    private final UserService userService;

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, UserService userService, NotificationService notificationService) {
        super(language, twigUtil, twigInformer);
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @PostMapping("/request")
    public ModelAndView notifications(Principal principal){
        return super.view("partials/notifications/notification-update-result", "notis",
                this.notificationService.findByUser(this.userService.findOneByUsername(principal.getName())));
    }

    @PostMapping("/remove-all")
    public ModelAndView removeAllAction(Principal principal){
        this.notificationService.remove(this.notificationService.findByUser(this.userService.findOneByUsername(principal.getName())));
        return super.view("partials/notifications/notification-update-result", "notis", new ArrayList<>());
    }

    @PostMapping("/remove/{id:[\\d]+}")
    public ModelAndView removeOneAction(@PathVariable Long id, Principal principal){
        this.notificationService.remove(this.notificationService.findOneById(id));
        return this.notifications(principal);
    }
    @PostMapping("/view/{id:[\\d]+}")
    public ModelAndView viewOneAction(@PathVariable Long id, Principal principal){
        this.notificationService.seeNotification(this.notificationService.findOneById(id));
        return this.notifications(principal);
    }

    @GetMapping("/mobile")
    public ModelAndView mobileNotificationsAction(Principal principal){
        return view("default/notifications", "notis", this.notificationService.findByUser(this.userService.findOneByUsername(principal.getName())));
    }

}
