package com.cyecize.skatefixers.areas.users.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
@PreAuthorize("isFullyAuthenticated()")
public class UserController  extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, UserService userService) {
        super(language, twigUtil, twigInformer);
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView profileAction(){

        return super.view("users/index");
    }
}
