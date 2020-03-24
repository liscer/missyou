package com.li.missyou.dto;

import com.li.missyou.dao.BannerItemRepository;
import com.li.missyou.dao.BannerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BannerDTOTest {
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private BannerItemRepository bannerItemRepository;

    @Test
    public void firstInsertData() {
        BannerDTO banner = new BannerDTO("b-1", "热销产品banner", "title", "img");
        BannerItemDTO bannerItem = new BannerItemDTO("img", 28L, 1L, "left");
        BannerItemDTO bannerItem1 = new BannerItemDTO("img", 26L, 1L, "right-top");
        List<BannerItemDTO> bannerItemDTOList = new ArrayList<>();
        bannerItemDTOList.add(bannerItem);
        bannerItemDTOList.add(bannerItem1);
        bannerItem.setBanner(banner);
        bannerItem1.setBanner(banner);
        banner.setBannerItemDTOList(bannerItemDTOList);
        bannerRepository.save(banner);
        bannerItemRepository.save(bannerItem);
        bannerItemRepository.save(bannerItem1);
    }

    @Test
    public void findBanner() {
        Optional<BannerDTO> result = bannerRepository.findById(1L);
        BannerDTO banner = result.get();
        System.out.println(banner.toString());
    }


}