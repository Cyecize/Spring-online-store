package com.cyecize.skatefixers.areas.admin.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
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
        this.userService.disableUser(this.findUserById(id));
        return "redirect:/admin/users/all";
    }
    @GetMapping("/users/enable/{id:[\\d]+}")
    public String enableUserAction(@PathVariable("id") Long id){
        this.userService.enableUser( this.findUserById(id));
        return "redirect:/admin/users/all";
    }

    @GetMapping("/users/promote/{id:[\\d]+}")
    public String promoteAction(@PathVariable("id") Long id){
        this.userService.changeUserRole( this.findUserById(id), UserRoleType.ROLE_WORKER);
        return "redirect:/admin/users/all";
    }

    @GetMapping("/users/demote/{id:[\\d]+}")
    public String demoteAction(@PathVariable("id") Long id){
        this.userService.changeUserRole( this.findUserById(id), UserRoleType.ROLE_USER);
        return "redirect:/admin/users/all";
    }

    private User findUserById(Long id){
        User user = this.userService.findOneById(id);
        if(user == null)
            throw new NotFoundException("User with id " + id + " does not exist!");
        return user;
    }
}
