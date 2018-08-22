package com.cyecize.skatefixers.areas.home.controllers;

import com.cyecize.skatefixers.areas.home.bindingModel.ContactUsBindingModel;
import com.cyecize.skatefixers.areas.home.services.BannerService;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.notifications.services.MailService;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HomeController extends BaseController {

    private final BannerService bannerService;

    private final BaseProductService productService;

    private final MailService mailService;

    private final UserService userService;

    @Autowired
    public HomeController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BannerService bannerService, BaseProductService productService, MailService mailService, UserService userService) {
        super(language, twigUtil, twigInformer);
        this.bannerService = bannerService;
        this.productService = productService;
        this.mailService = mailService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView homeAction(ModelAndView modelAndView) {
        modelAndView.addObject("sliderItems", bannerService.forgeIntoSlider());
        modelAndView.addObject("trendingProducts", this.productService.findTrendyProducts());
        return super.view("default/index", modelAndView);
    }

    @GetMapping("/about")
    public ModelAndView aboutUs(){

        return view("default/about");
    }

    @GetMapping("/views/clear")
    public  String clear(){
        this.productService.resetViews(); //TODO PLEASE REMOVE ME AFTER EXAM
        return "redirect:/";
    }

    @GetMapping("/contacts")
    public ModelAndView contactUs(ModelAndView modelAndView , Model model, @RequestParam(value = "info", required = false) String info){
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        modelAndView.addObject("info", info);
        modelAndView.addObject("bindingModel", model.asMap().get("contactBindingModel"));
        return view("default/contacts", modelAndView);
    }

    @PostMapping("/contacts/ask")
    public String askAction(@Valid @ModelAttribute("contactBindingModel")ContactUsBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("contactBindingModel", bindingModel);
        if(bindingResult.hasErrors())
            return "redirect:/contacts";
        final String format = "From: %s, phone: %s \r\n %s";
        this.mailService.sendMessageToUsers(this.userService.findByRole(UserRoleType.ROLE_WORKER), "question", String.format(format, bindingModel.getFullName(), bindingModel.getPhoneNumber(), bindingModel.getMessage()) );
        redirectAttributes.addAttribute("info", super.language.dictionary().messageSent());
        return "redirect:/contacts";
    }
}
