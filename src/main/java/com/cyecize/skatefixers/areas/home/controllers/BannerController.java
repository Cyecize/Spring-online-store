package com.cyecize.skatefixers.areas.home.controllers;

import com.cyecize.skatefixers.areas.googleDrive.util.MultipartToFileConverter;
import com.cyecize.skatefixers.areas.home.bindingModel.BannerBindingModel;
import com.cyecize.skatefixers.areas.home.bindingModel.EditBannerBindingModel;
import com.cyecize.skatefixers.areas.home.services.BannerService;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.products.bindingModels.validators.ValidImageValidator;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/banners")
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class BannerController extends BaseController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, BannerService bannerService) {
        super(language, twigUtil, twigInformer);
        this.bannerService = bannerService;
    }

    @GetMapping("/create")
    public ModelAndView createBannerRequest(ModelAndView modelAndView, Model model){
        modelAndView.addObject("banner", model.asMap().get("bannerBindingModel"));
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        return view("workers/banners/add-banner", modelAndView);
    }

    @PostMapping("/create")
    public String createBannerAction(@Valid @ModelAttribute("bannerBindingModel") BannerBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        redirectAttributes.addFlashAttribute("bannerBindingModel", bindingModel);
        if(bindingResult.hasErrors()){
            return "redirect:create";
        }
        this.bannerService.createBanner(bindingModel, MultipartToFileConverter.convert(bindingModel.getFile()));
        return "redirect:/worker-panel";
    }

    @GetMapping("/edit-list")
    public ModelAndView listAllBannersAction(){
        return this.view("workers/banners/list-for-edit", "banners", this.bannerService.findAll());
    }

    @GetMapping("/edit/{id:[\\d]+}")
    public ModelAndView editBannerRequest(ModelAndView modelAndView, Model model, @PathVariable Long id){
        modelAndView.addObject(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, model.asMap().get(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME));
        modelAndView.addObject("banner", this.bannerService.findOneById(id));
        return view("workers/banners/edit-banner", modelAndView);
    }

    @PostMapping("/edit/{id:[\\d]+}")
    public String editBannerAction(@Valid EditBannerBindingModel bindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id){
        redirectAttributes.addFlashAttribute(WebConstants.TWIG_BINDING_RESULT_OBJ_NAME, bindingResult);
        if(bindingResult.hasErrors())
            return "redirect:/banners/edit/" + id;
        if(bindingModel.getFile().isEmpty())
            this.bannerService.editBanner(bindingModel, id);
        else{
            if(!new ValidImageValidator().isValid(bindingModel.getFile(), null)){
                bindingResult.addError(new FieldError("", "file", "invalidImage"));
                return "redirect:/banners/edit/" + id;
            }
            this.bannerService.editBanner(bindingModel, id, MultipartToFileConverter.convert(bindingModel.getFile()));
        }
        return "redirect:/worker-panel";
    }
}
