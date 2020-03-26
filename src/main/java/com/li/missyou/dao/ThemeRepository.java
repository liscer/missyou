package com.li.missyou.dao;

import com.li.missyou.dto.ThemeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<ThemeDTO,Long> {
    Optional<ThemeDTO> findByName(String name);
}
