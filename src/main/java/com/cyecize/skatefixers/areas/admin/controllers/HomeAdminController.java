package com.cyecize.skatefixers.areas.admin.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class HomeAdminController  extends BaseController {

    private final UserService userService;

    @Autowired
    public HomeAdminController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, UserService userService) {
        super(language, twigUtil, twigInformer);
        this.userService = userService;
    }

    @GetMapping("/panel")
    public ModelAndView panelAction(){
        return view("admins/index");
    }

    @GetMapping("/users/all")
    public ModelAndView allUsersAction(){
        return view("admins/users/all-users", "users", this.userService.findAll());
    }

    @GetMapping("/users/disable/{id:[\\d]+}")
    public String disableUserAction(@PathVariable("id") Long id){
        User user = this.userService.findOneById(id);
        if(user == null)
            throw new NotFoundException("User with id " + id + " does not exist!");
        this.userService.disableUser(user);
        return "redirect:/admin/users/all";
    }
    @GetMapping("/users/enable/{id:[\\d]+}")
    public String enableUserAction(@PathVariable("id") Long id){
        User user = this.userService.findOneById(id);
        if(user == null)
            throw new NotFoundException("User with id " + id + " does not exist!");
        this.userService.enableUser(user);
        return "redirect:/admin/users/all";
    }
}
