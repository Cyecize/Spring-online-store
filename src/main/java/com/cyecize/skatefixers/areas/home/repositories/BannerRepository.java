package com.cyecize.skatefixers.areas.home.repositories;

import com.cyecize.skatefixers.areas.home.entities.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    List<Banner> findBannersByIsDisabled(boolean disabled);

    Banner findFirstByIsDisabledOrderByIdDesc(Boolean isDisabled);
}
