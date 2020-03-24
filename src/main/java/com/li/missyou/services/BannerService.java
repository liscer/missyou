package com.li.missyou.services;

import com.li.missyou.dao.BannerRepository;
import com.li.missyou.dto.BannerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    public BannerDTO findBannerByName(String name) {
        Optional<BannerDTO> result = bannerRepository.findByName(name);
        return result.orElse(null);
    }
}
