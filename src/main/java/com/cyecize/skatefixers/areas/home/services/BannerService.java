package com.cyecize.skatefixers.areas.home.services;

import com.cyecize.skatefixers.areas.home.bindingModel.BannerBindingModel;
import com.cyecize.skatefixers.areas.home.bindingModel.EditBannerBindingModel;
import com.cyecize.skatefixers.areas.home.entities.Banner;
import com.cyecize.skatefixers.areas.home.viewModels.HomeSliderBannerViewModel;

import java.io.File;
import java.util.List;

public interface BannerService {

    void createBanner(BannerBindingModel bindingModel, File image);

    void enableOrDisableBanner(Banner banner, boolean isDisabled);

    void editBanner(EditBannerBindingModel bindingModel, Long bannerId);

    void editBanner(EditBannerBindingModel bindingModel, Long bannerId, File background);

    List<Banner> findAll();

    List<Banner> findOnlyEnabled();

    List<Banner> findOnlyDisabled();

    List<HomeSliderBannerViewModel> forgeIntoSlider();

    Banner findLatestBanner();

    Banner findOneById(Long id);
}
