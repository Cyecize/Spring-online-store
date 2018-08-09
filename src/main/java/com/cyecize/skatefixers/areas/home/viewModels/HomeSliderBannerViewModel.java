package com.cyecize.skatefixers.areas.home.viewModels;

import com.cyecize.skatefixers.areas.home.entities.Banner;

public class HomeSliderBannerViewModel {

    private Banner banner;

    private Banner nextBanner;

    public HomeSliderBannerViewModel(Banner banner, Banner nextBanner) {
        this.banner = banner;
        this.nextBanner = nextBanner;
    }

    public HomeSliderBannerViewModel(){}

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Banner getNextBanner() {
        return nextBanner;
    }

    public void setNextBanner(Banner nextBanner) {
        this.nextBanner = nextBanner;
    }
}
