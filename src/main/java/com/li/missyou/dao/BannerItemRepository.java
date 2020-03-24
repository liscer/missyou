package com.li.missyou.dao;

import com.li.missyou.dto.BannerItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerItemRepository extends JpaRepository<BannerItemDTO,Long> {
}
