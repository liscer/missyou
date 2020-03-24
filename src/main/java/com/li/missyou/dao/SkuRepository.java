package com.li.missyou.dao;

import com.li.missyou.dto.SkuDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<SkuDTO,Long> {
}
