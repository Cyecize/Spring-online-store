package com.cyecize.skatefixers.areas.home.services;

import com.cyecize.skatefixers.areas.googleDrive.services.GoogleDriveManager;
import com.cyecize.skatefixers.areas.googleDrive.util.ImageIdExtractor;
import com.cyecize.skatefixers.areas.home.bindingModel.BannerBindingModel;
import com.cyecize.skatefixers.areas.home.bindingModel.EditBannerBindingModel;
import com.cyecize.skatefixers.areas.home.entities.Banner;
import com.cyecize.skatefixers.areas.home.repositories.BannerRepository;
import com.cyecize.skatefixers.areas.home.viewModels.HomeSliderBannerViewModel;
import com.cyecize.skatefixers.exceptions.NotFoundException;
import com.cyecize.skatefixers.util.ModelMerger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private static final String BANNER_FOLDER_ID = "1i4UUYzoB8sTpVyOKhv-14zhIB9VmzJGG";

    private final BannerRepository bannerRepository;

    private final ModelMapper modelMapper;

    private final GoogleDriveManager googleDriveManager;

    private final ModelMerger modelMerger;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository, ModelMapper modelMapper, GoogleDriveManager googleDriveManager, ModelMerger modelMerger) {
        this.bannerRepository = bannerRepository;
        this.modelMapper = modelMapper;
        this.googleDriveManager = googleDriveManager;
        this.modelMerger = modelMerger;
    }

    @Override
    @Async
    public void createBanner(BannerBindingModel bindingModel, File image) {
        Banner banner = this.modelMapper.map(bindingModel, Banner.class);
        String imgId = null;
        try {
            imgId = this.googleDriveManager.uploadFile(image, BANNER_FOLDER_ID, "ImgForBanner_" + banner.getTitle());
            banner.setBackgroundImageLink(ImageIdExtractor.setFormat(imgId));
            this.bannerRepository.saveAndFlush(banner);
        } catch (Exception e) {
            if (imgId != null)
                this.googleDriveManager.deleteFile(imgId);
            e.printStackTrace();
        }
    }

    @Override
    public void enableOrDisableBanner(Banner banner, boolean isDisabled) {
        banner.setDisabled(isDisabled);
        this.bannerRepository.saveAndFlush(banner);
    }

    @Override
    public void editBanner(EditBannerBindingModel bindingModel, Long bannerId) {
        Banner banner = this.findOneById(bannerId);
        banner = this.modelMerger.merge(bindingModel, banner);
        this.bannerRepository.saveAndFlush(banner);
    }

    @Override
    @Async
    public void editBanner(EditBannerBindingModel bindingModel, Long bannerId, File background) {
        this.editBanner(bindingModel, bannerId);
        Banner banner  = this.findOneById(bannerId);
        String newImgId = null;

        try{
            this.googleDriveManager.deleteFile(ImageIdExtractor.extractId(banner.getBackgroundImageLink()));
            newImgId = this.googleDriveManager.uploadFile(background, BANNER_FOLDER_ID, "ImgForBanner_" + banner.getTitle());
            banner.setBackgroundImageLink(ImageIdExtractor.setFormat(newImgId));
            this.bannerRepository.saveAndFlush(banner);
        }catch (Exception e){
            if(newImgId != null)
                this.googleDriveManager.deleteFile(newImgId);
        }
    }

    @Override
    public List<Banner> findAll() {
        return this.bannerRepository.findAll();
    }

    @Override
    public List<Banner> findOnlyEnabled() {
        return this.bannerRepository.findBannersByIsDisabledOrderByIdDesc(false);
    }

    @Override
    public List<Banner> findOnlyDisabled() {
        return this.bannerRepository.findBannersByIsDisabledOrderByIdDesc(true);
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

    @Override
    public Banner findOneById(Long id) {
        if (this.bannerRepository.findById(id).isPresent())
            return this.bannerRepository.findById(id).get();
        throw new NotFoundException("Banner was not found");
    }
}
