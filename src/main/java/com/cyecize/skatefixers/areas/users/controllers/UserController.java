package com.cyecize.skatefixers.areas.users.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.notifications.services.MailService;
import com.cyecize.skatefixers.areas.notifications.services.NotificationService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.areas.users.bindingModels.ChangePasswordBindingModel;
import com.cyecize.skatefixers.areas.users.bindingModels.EmailUserBindingModel;
import com.cyecize.skatefixers.areas.users.bindingModels.NotificationBindingModel;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
@PreAuthorize("isFullyAuthenticated()")
public class UserController  extends BaseController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MailService mailService;

    private final NotificationService notificationService;

    @Autowired
    public UserController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService, NotificationService notificationService) {
        super(language, twigUtil, twigInformer);
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
        this.notificationService = notificationService;
    }

    @GetMapping("/profile")
    public ModelAndView profileAction(){
        return super.view("users/index");
    }

    @GetMapping("/show/{id:[\\d]+}")
    public ModelAndView userAction(@PathVariable Long id, @RequestParam(value = "info", required = false) String info, ModelAndView modelAndView){
        modelAndView.addObject("user", this.userService.findOneById(id));
        modelAndView.addObject("info", info);
        return view("users/profile/profile", modelAndView);
    }

    @GetMapping("/password/edit")
    public ModelAndView editPasswordRequest(ModelAndView modelAndView, Model model){
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        return view("users/password/change", modelAndView);
    }

    @PostMapping("/password/edit")
    public String editPasswordAction(@Valid @ModelAttribute ChangePasswordBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        User user = this.userService.findOneByUsername(principal.getName());
        if(bindingResult.hasErrors())
            return "redirect:/users/password/edit";
        if(!this.bCryptPasswordEncoder.matches(bindingModel.getOldPassword(), user.getPassword())){
            bindingResult.addError(new FieldError("", "oldPassword", super.language.dictionary().passwordIsIncorrect()));
            return "redirect:/users/password/edit";
        }
        this.userService.changePassword(bindingModel.getPassword(), user);
        return "redirect:/users/profile";
    }

    @GetMapping("/remove")
    public ModelAndView removeUser(){
        return view("users/remove/remove");
    }

    @PostMapping("/remove")
    public String removeAction(Principal principal){
        User user = this.userService.findOneByUsername(principal.getName());
        if(user.getRoles().stream().filter(r -> r.getAuthority().equals(UserRoleType.ROLE_ADMIN.name())).findFirst().orElse(null) != null)
            throw new InternalException("Cannot remove admin!");
        this.userService.disableUser(user);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return "redirect:/";
    }

    @PostMapping("/workers/notify-user/{id:[\\d]+}")
    @PreAuthorize("hasAuthority('ROLE_WORKER')")
    public String notifyUser(RedirectAttributes redirectAttributes, NotificationBindingModel bindingModel, @PathVariable("id") Long id){
        this.notificationService.sendNotification(this.userService.findOneById(id),bindingModel.getMessage(), bindingModel.getUrl());
        redirectAttributes.addAttribute("info","Notification was sent!");
        return "redirect:/users/show/"+ id;
    }

    @PostMapping("/workers/email-user/{id:[\\d]+}")
    @PreAuthorize("hasAuthority('ROLE_WORKER')")
    public String notifyUser(RedirectAttributes redirectAttributes, EmailUserBindingModel bindingModel, @PathVariable("id") Long id){
        this.mailService.sendMessageToUser(this.userService.findOneById(id),bindingModel.getTopic(),bindingModel.getMessage());
        redirectAttributes.addAttribute("info","Email was sent!");
        return "redirect:/users/show/"+ id;
    }

}
