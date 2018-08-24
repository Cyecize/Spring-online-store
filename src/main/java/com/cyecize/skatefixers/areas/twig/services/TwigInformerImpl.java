package com.cyecize.skatefixers.areas.twig.services;

import com.cyecize.skatefixers.areas.home.entities.Banner;
import com.cyecize.skatefixers.areas.home.services.BannerService;
import com.cyecize.skatefixers.areas.notifications.models.Notification;
import com.cyecize.skatefixers.areas.notifications.services.NotificationService;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.products.entities.Category;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.products.services.BrandService;
import com.cyecize.skatefixers.areas.products.services.CategoryService;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwigInformerImpl implements TwigInformer {

    private final CategoryService categoryService;

    private final BaseProductService productService;

    private final BannerService bannerService;

    private final BrandService brandService;

    private final NotificationService notificationService;

    private User principal;

    @Autowired
    public TwigInformerImpl(CategoryService categoryService, BaseProductService productService, BannerService bannerService, BrandService brandService, NotificationService notificationService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.bannerService = bannerService;
        this.brandService = brandService;
        this.notificationService = notificationService;
    }

    @Override
    public List<Category> getCategoriesToDisplay() {
        return this.categoryService.findMainCategories();
    }

    @Override
    public List<BaseProduct> getNewProducts() {
        return this.getNewProducts(6);
    }

    @Override
    public List<BaseProduct> getNewProducts(int limit) {
        return this.productService.findNewProducts(limit);
    }

    @Override
    public List<Brand> getAllBrands() {
        return this.brandService.findAll();
    }

    @Override
    public Banner getBanner() {
        return this.bannerService.findLatestBanner();
    }

    @Override
    public User getUser() {
        return this.principal;
    }

    @Override
    public boolean hasRole(String role) {
        if (this.principal == null) return false;
        return this.principal.getRoles().stream().filter(r -> r.getAuthority().equals(role)).findFirst().orElse(null) != null;
    }

    @Override
    public void setUser(User principal) {
        this.principal = principal;
    }

    @Override
    public int getNotificationsCount() {
        if (this.getUser() == null) return 0;
        return this.notificationService.findNotSeenByUser(this.getUser()).size();
    }


}
