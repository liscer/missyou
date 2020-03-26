package com.li.missyou.services;

import com.li.missyou.dao.CategoryRepository;
import com.li.missyou.dto.CategoryDTO;
import com.li.missyou.vo.CategoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoriesVO getAllCategory() {
        List<CategoryDTO> rootCategory = categoryRepository.findByIsRoot(true);
        List<CategoryDTO> subCategory = categoryRepository.findByIsRoot(false);
        CategoriesVO categoriesVO = new CategoriesVO(rootCategory,subCategory);
        return categoriesVO;
    }

    public List<CategoryDTO> getAllGrid() {
        List<CategoryDTO> grids = categoryRepository.findByIsRoot(true);
        return grids;
    }

}
