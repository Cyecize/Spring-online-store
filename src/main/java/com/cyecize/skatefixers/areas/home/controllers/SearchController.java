package com.cyecize.skatefixers.areas.home.controllers;

import com.cyecize.skatefixers.areas.home.services.SearchService;
import com.cyecize.skatefixers.areas.home.viewModels.SearchPageViewModel;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.twig.services.TwigInformer;
import com.cyecize.skatefixers.areas.twig.util.TwigUtil;
import com.cyecize.skatefixers.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController extends BaseController {

    private final SearchService searchService;

    @Autowired
    public SearchController(LocalLanguage language, TwigUtil twigUtil, TwigInformer twigInformer, SearchService searchService) {
        super(language, twigUtil, twigInformer);
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ModelAndView userSearchAction(@RequestParam("search") String text, @PageableDefault(size = 6) Pageable pageable) {
        return this.finalizeSearch(text, pageable, () -> new SearchPageViewModel(text, searchService.searchProducts(text, pageable)));
    }

    @GetMapping("/search-worker")
    @PreAuthorize("hasAuthority('ROLE_WORKER')")
    public ModelAndView workerSearchAction(@RequestParam("search") String text, @PageableDefault(size = 6) Pageable pageable) {
        return this.finalizeSearch(text, pageable, () -> new SearchPageViewModel(text, searchService.searchAllProducts(text, pageable)));
    }

    private ModelAndView finalizeSearch(String text, Pageable pageable, Searchable searchable) {
        String nonProd = this.searchService.searchNonProducts(text);
        if (nonProd != null) return super.redirect(nonProd);
        return view("default/search-result", "viewModel", searchable.search());
    }
}

interface Searchable {
    SearchPageViewModel search();
}
