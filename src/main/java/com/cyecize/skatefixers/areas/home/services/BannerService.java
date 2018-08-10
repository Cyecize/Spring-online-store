package com.cyecize.skatefixers.areas.home.services;

import com.cyecize.skatefixers.areas.home.entities.Banner;
import com.cyecize.skatefixers.areas.home.viewModels.HomeSliderBannerViewModel;

import java.util.List;

public interface BannerService {

    List<Banner> findAll();

    List<Banner> findOnlyEnabled();

    List<Banner> findOnlyDisabled();

    List<HomeSliderBannerViewModel> forgeIntoSlider();

    Banner findLatestBanner();
}
