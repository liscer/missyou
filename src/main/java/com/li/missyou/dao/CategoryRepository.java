package com.li.missyou.dao;

import com.li.missyou.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryDTO,Long> {
    List<CategoryDTO> findByIsRoot(Boolean isRoot);
}
