package com.li.missyou.dao;

import com.li.missyou.dto.PersionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PersionDTO, Long> {
    PersionDTO findByName(String name);
}
