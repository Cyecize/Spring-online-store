package com.cyecize.skatefixers.areas.home.services;

import com.cyecize.skatefixers.areas.home.entities.Banner;
import com.cyecize.skatefixers.areas.home.repositories.BannerRepository;
import com.cyecize.skatefixers.areas.home.viewModels.HomeSliderBannerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public List<Banner> findAll() {
        return this.bannerRepository.findAll();
    }

    @Override
    public List<Banner> findOnlyEnabled() {
        return this.bannerRepository.findBannersByIsDisabled(false);
    }

    @Override
    public List<Banner> findOnlyDisabled() {
        return this.bannerRepository.findBannersByIsDisabled(true);
    }

    @Override
    public List<HomeSliderBannerViewModel> forgeIntoSlider() {
        List<HomeSliderBannerViewModel> slides = new ArrayList<>();

        List<Banner> entities = this.findOnlyEnabled();
        for (int i = 0; i < entities.size(); i++) {
            HomeSliderBannerViewModel slide;
            Banner banner = entities.get(i);

            if (i == 0) {
                slide = new HomeSliderBannerViewModel();
                slide.setBanner(banner);
                if (entities.size() > 1)
                    slide.setNextBanner(entities.get(1));
                else
                    slide.setNextBanner(banner);
            } else if (i == entities.size() - 1) {
                slide = new HomeSliderBannerViewModel(banner, entities.get(0));
            } else {
                slide = new HomeSliderBannerViewModel(banner, entities.get(i + 1));
            }
            slides.add(slide);
        }
        return slides;
    }

    @Override
    public Banner findLatestBanner() {
        return this.bannerRepository.findFirstByIsDisabledOrderByIdDesc(false);
    }
}
