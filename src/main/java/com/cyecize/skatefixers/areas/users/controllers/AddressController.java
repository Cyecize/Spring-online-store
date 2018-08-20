package com.cyecize.skatefixers.areas.users.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.services.TwigUtil;
import com.cyecize.skatefixers.areas.users.bindingModels.AddressBindingModel;
import com.cyecize.skatefixers.areas.users.services.AddressService;
import com.cyecize.skatefixers.areas.users.services.UserService;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import com.cyecize.skatefixers.http.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Component
@PreAuthorize("isFullyAuthenticated()")
@RequestMapping("/users/addresses")
public class AddressController extends BaseController {

    private final AddressService addressService;

    private final UserService userService;

    @Autowired
    public AddressController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, AddressService addressService, UserService userService) {
        super(language, twigUtil, twigInformer);
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView addAddressRequest(ModelAndView modelAndView, Model model) {
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        modelAndView.addObject("address", model.asMap().get("addressBindingModel"));
        return super.view("users/address/add-address", modelAndView);
    }

    @PostMapping("/add")
    public String addAddressAction(@Valid @ModelAttribute("addressBindingModel") AddressBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("addressBindingModel", bindingModel);
        if (bindingResult.hasErrors())
            return "redirect:add";
        this.addressService.createAddress(bindingModel, this.userService.findOneByUsername(principal.getName()));
        return "redirect:/users/addresses/all";
    }

    @PostMapping(value = "/remove/{id:[\\d]+}", produces = "application/json")
    @ResponseBody
    public JsonResponse removeAddressAction(@PathVariable Long id) {
        this.addressService.removeAddress(this.addressService.findById(id));
        return new JsonResponse("address was removed");
    }

    @GetMapping("/edit/{id:[\\d]+}")
    public ModelAndView editAddressRequest(ModelAndView modelAndView, Model model, @PathVariable("id") Long id) {
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        try {
            modelAndView.addObject("address", this.addressService.findById(id));
        } catch (JsonException e) {
            throw new NotFoundException("addr not found!");
        }
        return view("users/address/add-address", modelAndView);
    }

    @PostMapping("/edit/{id:[\\d]+}")
    public String editAddressAction(@Valid @ModelAttribute AddressBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        if (bindingResult.hasErrors())
            return "redirect:/users/addresses/edit/" + id;
        this.addressService.edit(this.addressService.findById(id), bindingModel);
        return "redirect:/users/addresses/all";
    }

    @GetMapping("/all")
    public ModelAndView allAddressesAction(Principal principal) {
        return view("users/address/all-addresses", "addresses", this.addressService.findAllAddresses(this.userService.findOneByUsername(principal.getName())));
    }
}
