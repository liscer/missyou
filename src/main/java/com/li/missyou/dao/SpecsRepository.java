package com.li.missyou.dao;

import com.li.missyou.dto.SpecDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecsRepository extends JpaRepository<SpecDTO,Long> {
    Optional<SpecDTO> findByParentId(Long id);
    Optional<SpecDTO> findByValue(String value);
}
