package com.li.missyou.api.v1;

import com.li.missyou.dto.CategoryDTO;
import com.li.missyou.services.CategoryService;
import com.li.missyou.vo.CategoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/all")
    public CategoriesVO getAllCategory() {
        return categoryService.getAllCategory();
    }

    @RequestMapping("/grid/all")
    public List<CategoryDTO> getAllGrid(){
        return categoryService.getAllGrid();
    }
}
