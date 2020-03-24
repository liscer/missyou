package com.li.missyou.dao;

import com.li.missyou.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryDTO,Long> {

}
