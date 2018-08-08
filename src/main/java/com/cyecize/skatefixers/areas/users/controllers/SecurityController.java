package com.cyecize.skatefixers.areas.users.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.users.bindingModels.UserRegisterBindingModel;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.services.TwigInformer;
import com.cyecize.skatefixers.services.TwigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class SecurityController extends BaseController {

    private final UserService userService;

    @Autowired
    public SecurityController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, UserService userService) {
        super(language, twigUtil, twigInformer);
        this.userService = userService;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView loginRequest(@RequestParam(required = false) String error, ModelAndView modelAndView, Model model) {
        if (error != null)
            modelAndView.addObject("error", "Invalid Username or password");
        return view("security/login", modelAndView);
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerRequest(ModelAndView modelAndView, Model model) {
        Map<String, Object> map = model.asMap();
        modelAndView.addObject("userRegisterModel", map.get("userRegisterModel"));
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, map.get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        return view("security/register", modelAndView);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerAction(@Valid @ModelAttribute("userRegisterModel") UserRegisterBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("userRegisterModel", bindingModel);
        if (bindingResult.hasErrors())
            return "redirect:/register";

        this.userService.createUser(bindingModel, bindingResult);
        return "redirect:/login";
    }
}
