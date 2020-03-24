package com.li.missyou.dao;

import com.li.missyou.dto.BannerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannerRepository extends JpaRepository<BannerDTO,Long> {
    Optional<BannerDTO> findByName(String name);
}
