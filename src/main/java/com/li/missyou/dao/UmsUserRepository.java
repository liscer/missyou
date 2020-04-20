package com.li.missyou.dao;

import com.li.missyou.dto.UmsUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UmsUserRepository extends JpaRepository<UmsUserDTO,String> {
    Optional<UmsUserDTO> queryByOpenId(String openId);
}
